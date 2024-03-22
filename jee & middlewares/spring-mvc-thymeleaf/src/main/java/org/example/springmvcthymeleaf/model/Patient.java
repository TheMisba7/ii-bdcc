package org.example.springmvcthymeleaf.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter
@Setter
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Name is mandatory.")
    private String name;
    @NotNull(message = "birthday is mandatory.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private Boolean sick;
    private Integer score;
}
