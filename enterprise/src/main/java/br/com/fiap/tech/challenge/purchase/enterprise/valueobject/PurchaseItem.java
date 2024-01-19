package br.com.fiap.tech.challenge.purchase.enterprise.valueobject;

import br.com.fiap.tech.challenge.enterprise.valueobject.Discount;
import br.com.fiap.tech.challenge.enterprise.valueobject.Price;
import br.com.fiap.tech.challenge.enterprise.valueobject.Quantity;
import br.com.fiap.tech.challenge.enterprise.valueobject.ValueObject;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Product;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serial;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Getter
@Accessors(fluent = true)
@EqualsAndHashCode(callSuper = true)
public class PurchaseItem extends ValueObject {
    @Serial
    private static final long serialVersionUID = -2071891141308689952L;

    @NotNull
    @Valid
    private final Product product;

    @NotNull
    @Valid
    private final Quantity quantity;

    @NotNull
    @Valid
    private final Price price;

    @NotNull
    @Valid
    private final Discount discount;

    @Builder(toBuilder = true)
    public PurchaseItem(@NotNull Product product, @NotNull Quantity quantity, @NotNull Discount discount, Price price) {
        this.product = product;
        this.price = defaultIfNull(price, this.product.price());
        this.quantity = quantity;
        this.discount = discount;

        validate();
    }

    public Price subTotal() {
        return price().multiply(quantity());
    }

    public Discount totalDiscount() {
        return discount().multiply(quantity());
    }

    public Price total() {
        return subTotal().subtract(totalDiscount().amount());
    }
}
