package br.com.fiap.tech.challenge.purchase.application.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Data
@Accessors(chain = true)
public class SimplePurchaseItemDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -3104288659566784282L;

    private SimpleProductDTO product;
    private Integer quantity;
}
