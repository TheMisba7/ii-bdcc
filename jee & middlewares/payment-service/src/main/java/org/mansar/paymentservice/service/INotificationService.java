package org.mansar.paymentservice.service;


import org.mansar.paymentservice.model.Email;

public interface INotificationService {
    void send(Email email);
}
