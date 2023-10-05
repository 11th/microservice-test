package org.test.microservice.usecase.model;

import lombok.Data;
import org.test.microservice.en.MessageType;

@Data
public class Message {
    private long id;
    private String from;
    private String to;
    private String text;
    private MessageType type;
}
