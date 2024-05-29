package org.mansar.digitalbanking.service.impl;

import lombok.RequiredArgsConstructor;
import org.mansar.digitalbanking.model.Email;
import org.mansar.digitalbanking.service.INotification;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationImpl implements INotification {
    private final JavaMailSender emailSender;
    @Override
    public void send(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email.getFrom());
        message.setSubject(email.getSubject());
        message.setTo(email.getTo());
        message.setText(email.getBody());
        emailSender.send(message);
    }
}
