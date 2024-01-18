package br.com.fiap.tech.challenge.purchase.enterprise.entity;

import br.com.fiap.tech.challenge.enterprise.entity.Entity;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus;
import br.com.fiap.tech.challenge.purchase.enterprise.valueobject.PurchaseItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Accessors(fluent = true)
@EqualsAndHashCode(callSuper = true)
public class Purchase extends Entity {

    @Serial
    private static final long serialVersionUID = -9196907733871633595L;

    private final Customer customer;

    @NotNull
    private final PurchaseStatus status;

    @NotNull
    private final LocalDate date;

    @NotNull
    @NotEmpty
    @Valid
    private final List<PurchaseItem> items;

    @NotNull
    private final Payment payment;

    @Builder(toBuilder = true)
    public Purchase(@Builder.ObtainVia(method = "uuid") UUID uuid,
                    Customer customer,
                    @NotNull PurchaseStatus status,
                    @NotNull LocalDate date,
                    @NotNull List<PurchaseItem> items,
                    @NotNull Payment payment) {
        super(uuid);

        this.customer = customer;
        this.status = status;
        this.date = date;
        this.items = items;
        this.payment = payment;

        validate();
    }

    public Purchase paid() {
        return toBuilder()
                .payment(payment.paid())
                .status(PurchaseStatus.PAID)
                .build();
    }

    public Purchase made() {
        return toBuilder()
                .status(PurchaseStatus.MADE)
                .build();
    }

    public Purchase making() {
        return toBuilder()
                .status(PurchaseStatus.MAKING)
                .build();
    }

    public Purchase delivered() {
        return toBuilder()
                .status(PurchaseStatus.DELIVERED)
                .build();
    }
}
