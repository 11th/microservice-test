package org.test.microservice.database.entity;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.test.microservice.en.MessageType;

@Data
public class CustomMessageEntity {
    @NotNull
    private MessageType type;
    @NotNull
    private Long count;
}
