package org.test.microservice.service;

import org.test.microservice.en.MessageType;
import org.test.microservice.usecase.model.Message;

import java.util.List;

public interface MessageService {
    List<Message> getAll();

    Message getById(long id);

    List<Message> getByType(MessageType type);

    void save(Message message);
}
