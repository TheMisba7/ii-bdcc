package org.mansar.paymentservice.service.impl;

import lombok.extern.log4j.Log4j2;
import org.mansar.paymentservice.model.Email;
import org.mansar.paymentservice.service.INotificationService;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class INotificationServiceImpl implements INotificationService {
    @Override
    public void send(Email email) {
        System.out.println(Thread.currentThread().getName());
       log.info("email from " + email.getFrom());
       log.info("email to " + email.getTo()[0]);
       log.info("email body " + email.getBody());
    }
}
