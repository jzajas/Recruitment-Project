package com.jzajas.task.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;


@Getter
public class CampaignUpdateDTO {

    @NotBlank(message = "Campaign name must be specified")
    private String name;

    @NotEmpty(message = "At least one keyword must be specified")
    private List<String> keywords;

    @NotNull(message = "Bid amount must be specified")
    @DecimalMin(value = "5.00", message = "Bid amount must be at least 5")
    private BigDecimal bidAmount;

    @NotNull(message = "Campaign fund must be specified")
    @DecimalMin(value = "00.00", message = "Campaign fund must be at least 0")
    private BigDecimal fund;

    @NotNull(message = "Status  must be specified")
    private Boolean status;

    private String town;

    @NotNull(message = "Radius must be specified")
    @Min(value = 1, message = "Radius must be at least 1 km")
    private Integer radius;
}
