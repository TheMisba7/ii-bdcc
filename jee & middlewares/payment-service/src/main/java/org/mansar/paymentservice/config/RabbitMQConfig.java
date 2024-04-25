package org.mansar.paymentservice.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    private final ConfigProperties configProperties;

    public RabbitMQConfig(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    @Bean
    public Queue emailQueue() {
        return new Queue(configProperties.getEmailQueueName());
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(configProperties.getEmailExchangeName());
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(emailQueue())
                .to(topicExchange())
                .with(configProperties.getEmailBindingName());
    }
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    @Bean
    MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
}
