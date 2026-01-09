package com.jzajas.task.dto;

import com.jzajas.task.model.Campaign;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public class UserCreationDTO {

    @NotBlank(message = "Firstname must be specified")
    private String firstname;

    @NotBlank(message = "Lastname must be specified")
    private String lastname;

    @NotNull(message = "You must provide starting balance")
    @DecimalMin(value = "50.00", message = "Starting balance must be at least 50.00")
    private BigDecimal balance;
}
