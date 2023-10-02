package org.test.microservice.api.controller;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;
import org.test.microservice.en.MessageType;
import org.test.microservice.api.dto.MessageDto;
import org.test.microservice.api.presenter.MessagePresenter;

import java.util.List;

@RestController("/v1/message")
@RequiredArgsConstructor
public class MessageController {

  private final MessagePresenter messagePresenter;

  @GetMapping
  @NotNull
  public List<MessageDto> getAll() {
    return messagePresenter.getAll();
  }

  @GetMapping("/{id}")
  @NotNull
  public MessageDto getById(@PathVariable long id) {
    return messagePresenter.getById(id);
  }

  @GetMapping(params = "type")
  @NotNull
  public List<MessageDto> getById(@PathVariable MessageType type) {
    return messagePresenter.getByType(type);
  }
}
