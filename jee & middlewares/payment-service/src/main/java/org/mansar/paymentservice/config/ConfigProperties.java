package org.mansar.paymentservice.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ConfigProperties {
    @Value("${rabbitmq.queue.email.name}")
    private String emailQueueName;
    @Value("${rabbitmq.exchange.email.name}")
    private String emailExchangeName;
    @Value("${rabbitmq.queue.email.name}")
    private String emailBindingName;
}
