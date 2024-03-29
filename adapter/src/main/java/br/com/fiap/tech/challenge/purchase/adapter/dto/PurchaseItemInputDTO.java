package br.com.fiap.tech.challenge.purchase.adapter.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class PurchaseItemInputDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -3104288659566784282L;

    private SingleProductInputDTO product;
    private Integer quantity;
    private BigDecimal subTotal;
    private BigDecimal total;
    private BigDecimal discount;
}
