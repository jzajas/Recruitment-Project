package com.jzajas.task.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@Setter
@Getter
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
