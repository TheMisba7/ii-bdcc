package org.mansar.paymentservice.service;

import lombok.extern.slf4j.Slf4j;
import org.mansar.paymentservice.config.ConfigProperties;
import org.mansar.paymentservice.model.Email;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQConsumer {
    private final INotificationService notificationService;

    public RabbitMQConsumer(INotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = {"${rabbitmq.queue.email.name}"})
    public void onPaymentStatusChange(Email email) {
        notificationService.send(email);
    }
}
