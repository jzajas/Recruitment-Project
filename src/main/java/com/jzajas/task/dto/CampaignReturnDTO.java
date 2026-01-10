package com.jzajas.task.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@Setter
public class CampaignReturnDTO {

    private String name;

    private String ownerName;

    private  String ownerSurname;

    private List<String> keywords;

    private BigDecimal bidAmount;

    private BigDecimal fund;

    private Boolean status;

    private String town;

    private Integer radius;
}
