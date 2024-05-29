package org.mansar.digitalbanking.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class NewCustomerDTO {
    private String firstname;
    private String lastname;
    private String email;
    private List<Long> roleIds;
}
