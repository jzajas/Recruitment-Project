package com.jzajas.task.dto;

import lombok.Setter;

import java.math.BigDecimal;


@Setter
public class UserReturnDTO {

    private String firstname;

    private String surname;

    private String email;

    private BigDecimal balance;
}
