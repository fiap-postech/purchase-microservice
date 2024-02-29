package br.com.fiap.tech.challenge.purchase.enterprise.enums;

import lombok.Getter;

import java.util.List;

@Getter
public enum PurchaseStatus {

    DELIVERED("Entregue"),
    MADE("Pronto", DELIVERED),
    MAKING("Preparando", MADE),
    WAITING_MAKE("Em fila para preparo", MAKING),
    PAID_SUCCESS("Pagamento confirmado", WAITING_MAKE),
    PAID_ERROR("Falha no pagamento"),
    WAITING_PAYMENT("Aguardando Pagamento", PAID_SUCCESS, PAID_ERROR),
    CREATED("Criado", WAITING_PAYMENT);

    private final List<PurchaseStatus> allowed;
    private final String description;

    PurchaseStatus(String description, PurchaseStatus... allowedStatus) {
        this.description = description;
        this.allowed = List.of(allowedStatus);
    }

    public boolean allowChange(PurchaseStatus status) {
        return allowed.contains(status);
    }
}
