package org.test.microservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.test.microservice.database.repository.MessageRepository;
import org.test.microservice.en.MessageType;
import org.test.microservice.usecase.model.Message;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

  private final MessageRepository messageRepository;

  @Override
  public List<Message> getAll() {
    return null;
  }

  @Override
  public Message getById() {
    return null;
  }

  @Override
  public List<Message> getByType(MessageType type) {
    return null;
  }

  @Override
  public void save(Message message) {

  }
}
