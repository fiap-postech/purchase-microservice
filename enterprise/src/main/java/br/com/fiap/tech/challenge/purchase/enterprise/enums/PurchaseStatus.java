package br.com.fiap.tech.challenge.purchase.enterprise.enums;

import java.util.List;

public enum PurchaseStatus {

    DELIVERED,
    MADE(DELIVERED),
    MAKING(MADE),
    WAITING_MAKE(MAKING),
    PAID(WAITING_MAKE),
    CREATED(PAID);
    private final List<PurchaseStatus> allowed;

    PurchaseStatus(PurchaseStatus... allowedStatus) {
        this.allowed = List.of(allowedStatus);
    }

    public boolean allowChange(PurchaseStatus status) {
        return allowed.contains(status);
    }
}
