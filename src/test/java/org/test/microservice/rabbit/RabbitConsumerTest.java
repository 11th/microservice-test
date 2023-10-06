package org.test.microservice.rabbit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.test.library.MetricService;
import org.test.microservice.rabbit.dto.MessageRabbitDto;
import org.test.microservice.rabbit.dto.mapper.MessageRabbitDtoMapper;
import org.test.microservice.usecase.SaveMessageUseCase;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class RabbitConsumerTest {
    @Mock
    private SaveMessageUseCase saveMessageUseCase;
    @Mock
    private MetricService metricService;
    @Spy
    private MessageRabbitDtoMapper messageRabbitDtoMapper;

    @InjectMocks
    private RabbitConsumer rabbitConsumer;

    @Test
    @DisplayName("Process message from RabbitMQ -> Success")
    public void processMessageQueue_success() {
        //Given
        MessageRabbitDto expected = new MessageRabbitDto();
        expected.setId(1L);
        expected.setFrom("from");
        expected.setTo("to");
        expected.setText("text");
        expected.setType(10);
        //When
        rabbitConsumer.processMessageQueue(List.of(expected));
        //Then
        Mockito.verify(saveMessageUseCase, times(1)).saveAll(messageRabbitDtoMapper.mapToMessageList(List.of(expected)));
        Mockito.verify(metricService, times(1)).save(Map.of("SMS", 1L));
    }
}