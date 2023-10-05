package org.test.microservice.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.test.microservice.en.MessageType;
import org.test.microservice.service.MessageService;
import org.test.microservice.usecase.model.Message;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetMessageUseCaseImpl implements GetMessageUseCase {
    private final MessageService messageService;

    @Override
    public List<Message> getAll() {
        return messageService.getAll();
    }

    @Override
    public Message getById(long id) {
        return messageService.getById(id);
    }

    @Override
    public List<Message> getByType(MessageType messageType) {
        return messageService.getByType(messageType);
    }
}
