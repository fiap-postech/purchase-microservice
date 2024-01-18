package br.com.fiap.tech.challenge.purchase.application.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class PurchaseItemDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -3104288659566784282L;

    private ProductDTO product;
    private Integer quantity;
    private BigDecimal fullPrice;
    private BigDecimal price;
    private BigDecimal discount;
}
