package com.example.ozon2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoodDto {

    private Long id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private int quantity;

    @NotNull
    private BigDecimal price;

    @NotNull
    private String goodStatus;
}
