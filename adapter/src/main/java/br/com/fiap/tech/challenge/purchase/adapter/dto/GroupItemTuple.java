package br.com.fiap.tech.challenge.purchase.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class GroupItemTuple {

    private String productId;
    private BigDecimal price;
    private BigDecimal discount;

}
