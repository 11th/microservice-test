package org.test.microservice.api.dto;

import lombok.Data;
import org.test.microservice.en.MessageType;

import java.io.Serializable;

@Data
public class MessageDto implements Serializable {
    private long id;
    private String from;
    private String to;
    private String text;
    private MessageType type;
}
