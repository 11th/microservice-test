package org.test.microservice.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.test.microservice.rabbit.RabbitProperties;
import org.springframework.validation.Validator;

@Configuration
@RequiredArgsConstructor
public class RabbitConfiguration implements RabbitListenerConfigurer {
    private final RabbitProperties rabbitProperties;
    private final Validator validator;

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setValidator(validator);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory();
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public SimpleRabbitListenerContainerFactory messageListenerContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factoryConfigurer.configure(factory, connectionFactory());
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return factory;
    }

    @Bean
    public Declarables createFailureMessageSchema() {
        return new Declarables(
                new DirectExchange(rabbitProperties.getFailureExchange()),
                new Queue(rabbitProperties.getFailureQueue()),
                new Binding(rabbitProperties.getFailureQueue(),
                        Binding.DestinationType.QUEUE,
                        rabbitProperties.getFailureExchange(),
                        rabbitProperties.getFailureRouting(),
                        null)
        );
    }

    @Bean
    public Queue messageQueue() {
        return QueueBuilder.durable(rabbitProperties.getQueue())
                .withArgument("x-dead-letter-exchange", rabbitProperties.getFailureExchange())
                .withArgument("x-dead-letter-routing-key", rabbitProperties.getFailureRouting())
                .build();
    }
}
