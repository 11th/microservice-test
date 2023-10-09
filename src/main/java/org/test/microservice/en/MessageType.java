package org.test.microservice.en;

import org.test.microservice.exception.MessageTypeNotFoundException;

import java.util.Arrays;

public enum MessageType {
    SMS(10),
    EMAIL(20),
    TELEGRAM(30);

    private final int id;

    MessageType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static MessageType fromId(int id) {
        return Arrays.stream(values())
                .filter(mt -> mt.getId() == id)
                .findFirst()
                .orElseThrow(() -> new MessageTypeNotFoundException(String.format("MessageType with id %d not found", id)));
    }
}
