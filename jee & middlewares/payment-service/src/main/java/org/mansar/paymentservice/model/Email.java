package org.mansar.paymentservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class Email {
    private String[] to;
    private String from;
    private String body;
}
