package org.test.microservice.usecase;

import org.jetbrains.annotations.NotNull;
import org.test.microservice.usecase.model.Message;

import java.util.List;

public interface SaveMessageUseCase {
    void saveAll(@NotNull List<Message> message);
}
