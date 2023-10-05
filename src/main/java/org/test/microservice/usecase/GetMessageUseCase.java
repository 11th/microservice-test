package org.test.microservice.usecase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.test.microservice.en.MessageType;
import org.test.microservice.usecase.model.Message;

import java.util.List;

public interface GetMessageUseCase {
    Page<Message> getAll(Pageable pageable);

    Message getById(long id);

    List<Message> getByType(MessageType messageType);
}
