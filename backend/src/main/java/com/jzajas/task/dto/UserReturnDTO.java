package com.jzajas.task.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Setter
@Getter
public class UserReturnDTO {

    private Long id;

    private String firstname;

    private String surname;

    private String email;

    private BigDecimal balance;
}
