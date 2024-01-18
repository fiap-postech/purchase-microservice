package br.com.fiap.tech.challenge.purchase.rest.resource.request;

import br.com.fiap.tech.challenge.purchase.enterprise.entity.Product;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.ProductCategory;
import br.com.fiap.tech.challenge.rest.common.request.Request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ProductRequest extends Request<Product> {

    @Serial
    private static final long serialVersionUID = 4921993396391178014L;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @PositiveOrZero
    private BigDecimal price;

    @NotBlank
    private String image;

    @NotNull
    private ProductCategory category;
}
