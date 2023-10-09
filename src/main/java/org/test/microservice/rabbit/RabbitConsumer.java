package org.test.microservice.rabbit;

import jakarta.validation.*;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.test.library.MetricService;
import org.test.microservice.en.MessageType;
import org.test.microservice.rabbit.dto.MessageRabbitDto;
import org.test.microservice.rabbit.dto.mapper.MessageRabbitDtoMapper;
import org.test.microservice.usecase.SaveMessageUseCase;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Log4j2
@AllArgsConstructor
@Validated
public class RabbitConsumer {
    private final SaveMessageUseCase saveMessageUseCase;
    private final MessageRabbitDtoMapper messageRabbitDtoMapper;
    private final MetricService metricService;

    @RabbitListener(queues = "${rabbitmq.message.queue}", containerFactory = "messageListenerContainerFactory")
    public void processMessageQueue(@Valid @Payload List<MessageRabbitDto> messages) {
        saveMessages(messages);
        saveMetrics(messages);
    }

    private void saveMessages(List<MessageRabbitDto> messages) {
        log.info("Messages: {}", messages);
        saveMessageUseCase.saveAll(messageRabbitDtoMapper.mapToMessageList(messages));
    }

    private void saveMetrics(List<MessageRabbitDto> messages) {
        Map<String, Long> metrics = messages.stream()
                .collect(Collectors.groupingBy(message -> MessageType.fromId(message.getType()).name(), Collectors.counting()));
        log.info("Metrics: {}", metrics);
        metricService.save(metrics);
    }

    @RabbitListener(queues = "${rabbitmq.message.failure-queue}")
    public void processFallBackMessage(List<MessageRabbitDto> messages) {
        log.info("Message failed: {}", messages);
    }
}
