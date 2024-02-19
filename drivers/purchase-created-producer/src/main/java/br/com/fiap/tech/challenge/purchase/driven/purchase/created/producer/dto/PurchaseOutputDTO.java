package br.com.fiap.tech.challenge.purchase.driven.purchase.created.producer.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PurchaseOutputDTO {

    private String id;
    private BigDecimal total;
    private List<PurchaseItemOutputDTO> items;

}
