package org.mansar.digitalbanking.service;

import org.mansar.digitalbanking.model.Email;

public interface INotification {
    void send(Email email);
}
