package org.test.microservice.usecase;

import org.springframework.stereotype.Service;
import org.test.microservice.usecase.model.Message;

import java.util.List;

@Service
public class GetMessageUseCaseImpl implements GetMessageUseCase {

  @Override
  public List<Message> getAll() {
    return null;
  }

  @Override
  public Message getById(int id) {
    return null;
  }
}
