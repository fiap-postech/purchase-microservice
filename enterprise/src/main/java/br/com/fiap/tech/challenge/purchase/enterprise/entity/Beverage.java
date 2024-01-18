package br.com.fiap.tech.challenge.purchase.enterprise.entity;

import br.com.fiap.tech.challenge.enterprise.valueobject.Image;
import br.com.fiap.tech.challenge.enterprise.valueobject.Price;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.ProductCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.util.UUID;

@Getter
@Accessors(fluent = true)
public class Beverage extends Product {

    @Serial
    private static final long serialVersionUID = 8271359996195513007L;

    @Builder(toBuilder = true)
    protected Beverage(
            @Builder.ObtainVia(method = "uuid") UUID uuid,
            @Builder.ObtainVia(method = "name") String name,
            @Builder.ObtainVia(method = "description") String description,
            @Builder.ObtainVia(method = "price") Price price,
            @Builder.ObtainVia(method = "image") Image image,
            @Builder.ObtainVia(method = "enabled") boolean enabled
    ) {
        super(uuid, name, description, price, image, enabled);

        validate();
    }

    @Override
    public ProductCategory category() {
        return ProductCategory.BEVERAGE;
    }

    @Override
    public Product enable() {
        return toBuilder()
                .enabled(true)
                .build();
    }

    @Override
    public Product disable() {
        return toBuilder()
                .enabled(false)
                .build();
    }
}
