package br.com.fiap.tech.challenge.purchase.enterprise.entity;

import br.com.fiap.tech.challenge.enterprise.entity.Entity;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.PaymentStatus;
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
public class Payment extends Entity {

    @Serial
    private static final long serialVersionUID = 2733420553391362792L;

    @NotNull
    @NotBlank
    private final String id;

    @NotNull
    private final PaymentStatus status;

    @Builder(toBuilder = true)
    public Payment(@Builder.ObtainVia(method = "uuid") UUID uuid,
                   @NotNull String id,
                   @NotNull PaymentStatus status) {
        super(uuid);

        this.id = id;
        this.status = status;

        validate();
    }
}
