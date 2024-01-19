package br.com.fiap.tech.challenge.purchase.enterprise.entity;

import br.com.fiap.tech.challenge.enterprise.entity.Entity;
import br.com.fiap.tech.challenge.enterprise.valueobject.Price;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.util.UUID;

@Getter
@Accessors(fluent = true)
@EqualsAndHashCode(callSuper = true)
public class Product extends Entity {
    @Serial
    private static final long serialVersionUID = -556035981231420003L;

    @NotBlank
    private final String name;

    @NotBlank
    private final String description;

    @NotNull
    @Valid
    private final Price price;

    @Builder(toBuilder = true)
    protected Product(@Builder.ObtainVia(method = "uuid") UUID uuid, String name, String description, @NotNull Price price) {
        super(uuid);

        this.name = name;
        this.description = description;
        this.price = price;

        validate();
    }
}
