package br.com.fiap.tech.challenge.purchase.driven.purchase.created.producer.dto;

import lombok.Data;

@Data
public class PurchaseItemOutputDTO {

    private String title;
    private String description;
    private String categoryId;
    private Integer quantity;
    private String currencyId;
    private String unitPrice;

}
