package com.jzajas.task.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;


@Getter
public class UserCreationDTO {

    @NotBlank(message = "Firstname must be specified")
    private String firstname;

    @NotBlank(message = "Surname must be specified")
    private String surname;

    @Email(message = "Must be a valid email format")
    @NotBlank(message = "Email must be specified")
    private String email;

    @NotNull(message = "You must provide starting balance")
    @DecimalMin(value = "00.00", message = "Starting balance must be at least 00.00")
    private BigDecimal balance;
}
