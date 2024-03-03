package br.com.fiap.tech.challenge.purchase.enterprise.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    CREATED("Criado"),
    PAID("Pago"),
    ERROR("Erro no pagamento");

    private final String description;

    PaymentStatus(String description) {
        this.description = description;
    }
}
