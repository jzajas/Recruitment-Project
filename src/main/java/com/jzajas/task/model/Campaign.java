package com.jzajas.task.model;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@Data
@Entity
@Table(name = "campaigns")
@NoArgsConstructor
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    @Column(name = "name", nullable = false)
    private String name;

    @ElementCollection
    @Column(name = "keywords", nullable = false)
    private List<String> keywords;

    @Column(name = "bid", nullable = false)
    private BigDecimal bidAmount;

    @Column(name = "fund", nullable = false)
    private BigDecimal fund;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "town", nullable = true)
    private String town;

    @Column(name = "radius", nullable = false)
    private Integer radius;
}
