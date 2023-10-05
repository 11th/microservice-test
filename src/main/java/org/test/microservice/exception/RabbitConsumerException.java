package org.test.microservice.exception;

public class RabbitConsumerException extends RuntimeException {
    public RabbitConsumerException(String message) {
        super(message);
    }
}
