package org.test.microservice.usecase;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.test.microservice.service.MessageService;
import org.test.microservice.usecase.model.Message;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaveMessageUseCaseImpl implements SaveMessageUseCase {
    private final MessageService messageService;

    @Override
    public void saveAll(@NotNull List<Message> messageList) {
        for (Message message : messageList) {
            messageService.save(message);
        }
    }
}
