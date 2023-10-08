package org.test.microservice.api.controller;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.test.microservice.en.MessageType;
import org.test.microservice.api.dto.MessageDto;
import org.test.microservice.api.presenter.MessagePresenter;

import java.util.List;

@RestController
@RequestMapping("/v1/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessagePresenter messagePresenter;

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
}
