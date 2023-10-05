package org.test.microservice.rabbit;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rabbitmq.message")
@Data
public class RabbitProperties {
    private String queue;
    private String failureExchange;
    private String failureQueue;
    private String failureRouting;
}
