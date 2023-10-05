package org.test.microservice.usecase;

import org.test.microservice.en.MessageType;
import org.test.microservice.usecase.model.Message;

import java.util.List;

public interface GetMessageUseCase {
    List<Message> getAll();

    Message getById(long id);

    List<Message> getByType(MessageType messageType);
}
