package org.mansar.digitalbanking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Email {
    private String subject;
    private String from;
    private String to;
    private String body;
}
