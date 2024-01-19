package br.com.fiap.tech.challenge.purchase.rest.resource.response;

import br.com.fiap.tech.challenge.rest.common.response.Response;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseItemResponse extends Response {
    @Serial
    private static final long serialVersionUID = -3529381046984748130L;

    private ProductResponse product;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal discount;
}
