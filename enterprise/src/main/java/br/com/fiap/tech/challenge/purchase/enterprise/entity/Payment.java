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
import java.time.LocalDateTime;
import java.util.UUID;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

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

    @NotNull
    private final LocalDateTime created;

    @Builder(toBuilder = true)
    public Payment(@Builder.ObtainVia(method = "uuid") UUID uuid,
                   @NotNull String id,
                   @NotNull PaymentStatus status,
                   LocalDateTime created) {
        super(uuid);

        this.id = id;
        this.status = status;
        this.created = defaultIfNull(created, LocalDateTime.now());

        validate();
    }
}
