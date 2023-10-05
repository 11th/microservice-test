package org.test.microservice.api.controller;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.microservice.en.MessageType;
import org.test.microservice.api.dto.MessageDto;
import org.test.microservice.api.presenter.MessagePresenter;
import org.test.microservice.rabbit.dto.MessageRabbitDto;

import java.util.List;

@RestController
@RequestMapping("/v1/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessagePresenter messagePresenter;
    private final RabbitTemplate template;

    @GetMapping
    @NotNull
    public Page<MessageDto> getAll(Pageable pageable) {
        return messagePresenter.getAll(pageable);
    }

    @GetMapping("/{id}")
    @NotNull
    public MessageDto getById(@PathVariable Long id) {
        return messagePresenter.getById(id);
    }

    @GetMapping(params = "type")
    @NotNull
    public List<MessageDto> getByType(@RequestParam MessageType type) {
        return messagePresenter.getByType(type);
    }


    //todo: to test only!
    @PostMapping
    public ResponseEntity<String> addMessages(@RequestBody List<MessageRabbitDto> messages) {
        template.convertAndSend("", "sms.data", messages);
        return ResponseEntity.ok("Messages added to queue");
    }
}
