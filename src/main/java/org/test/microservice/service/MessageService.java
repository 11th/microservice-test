package org.test.microservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.test.microservice.database.repository.TypeStatistic;
import org.test.microservice.en.MessageType;
import org.test.microservice.usecase.model.Message;

import java.util.List;

public interface MessageService {
    Page<Message> getAll(Pageable pageable);

    Message getById(long id);

    List<Message> getByType(MessageType type);

    List<TypeStatistic> getTypeStatistic();

    void save(Message message);
}
