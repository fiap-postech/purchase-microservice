package br.com.fiap.tech.challenge.purchase.enterprise.entity;

import br.com.fiap.tech.challenge.enterprise.entity.Entity;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus;
import br.com.fiap.tech.challenge.purchase.enterprise.valueobject.PurchaseItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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

import static br.com.fiap.tech.challenge.purchase.enterprise.error.ApplicationError.INVALID_PURCHASE_STATUS_CHANGE;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Getter
@Accessors(fluent = true)
@EqualsAndHashCode(callSuper = true)
public class Purchase extends Entity {

    @Serial
    private static final long serialVersionUID = -9196907733871633595L;

    @Valid
    private final Customer customer;

    @NotNull
    private final PurchaseStatus status;

    @NotNull
    private final LocalDate date;

    @NotNull
    @NotEmpty
    @Valid
    private final List<PurchaseItem> items;

    @Valid
    private final Payment payment;

    @NotBlank
    private final String externalId;

    @NotBlank
    private final String code;

    @Builder(toBuilder = true)
    public Purchase(@Builder.ObtainVia(method = "uuid") UUID uuid,
                    Customer customer,
                    @NotNull PurchaseStatus status,
                    @NotNull LocalDate date,
                    @NotNull List<PurchaseItem> items,
                    Payment payment,
                    @NotNull String externalId,
                    String code) {
        super(uuid);

        this.customer = customer;
        this.status = status;
        this.date = date;
        this.items = items;
        this.payment = payment;
        this.externalId = externalId;
        this.code = defaultIfNull(code, generateCode());

        validate();
    }

    public Purchase created() {
        return updateStatus(PurchaseStatus.CREATED);
    }

    public Purchase waitingPayment() {
        return updateStatus(PurchaseStatus.WAITING_PAYMENT);
    }

    public Purchase paidSuccessful() {
        return updateStatus(PurchaseStatus.PAID_SUCCESS);
    }

    public Purchase paidFail() {
        return updateStatus(PurchaseStatus.PAID_ERROR);
    }

    public Purchase waitMake() {
        return updateStatus(PurchaseStatus.WAITING_MAKE);
    }

    public Purchase made() {
        return updateStatus(PurchaseStatus.MADE);
    }

    public Purchase making() {
        return updateStatus(PurchaseStatus.MAKING);
    }

    public Purchase delivered() {
        return updateStatus(PurchaseStatus.DELIVERED);
    }

    public boolean isWaitingMake() {
        return status() == PurchaseStatus.WAITING_MAKE;
    }

    private Purchase updateStatus(PurchaseStatus status) {
        if (!status().allowChange(status)) {
            throw new ApplicationException(INVALID_PURCHASE_STATUS_CHANGE, status(), status);
        }

        return toBuilder()
                .status(status)
                .build();
    }

    private String generateCode() {
        return UUID.randomUUID().toString().substring(0, 4);
    }
}
