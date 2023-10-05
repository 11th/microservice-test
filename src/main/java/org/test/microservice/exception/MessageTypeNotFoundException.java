package org.test.microservice.exception;

public class MessageTypeNotFoundException extends RuntimeException {
    public MessageTypeNotFoundException(String message) {
        super(message);
    }
}
