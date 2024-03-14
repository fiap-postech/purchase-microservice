package br.com.fiap.tech.challenge.purchase.adapter.dto;

import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

import static br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus.CREATED;
import static br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus.DELIVERED;
import static br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus.MADE;
import static br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus.MAKING;
import static br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus.PAID_ERROR;
import static br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus.PAID_SUCCESS;
import static br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus.WAITING_MAKE;
import static br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus.WAITING_PAYMENT;

@Getter
public enum NotificationTemplate {

    DEFAULT("PurchaseStatusDefaultTemplate", CREATED, MADE, MAKING, WAITING_MAKE, PAID_SUCCESS, DELIVERED),
    PAYMENT_CREATED("PurchaseStatusPaymentTemplate", WAITING_PAYMENT),
    PAYMENT_ERROR("PurchaseStatusPaymentErrorTemplate", PAID_ERROR);

    private final List<PurchaseStatus> allowed;
    private final String template;

    NotificationTemplate(String template, PurchaseStatus... allowedStatuses) {
        this.template = template;
        this.allowed = List.of(allowedStatuses);
    }

    public static NotificationTemplate getCurrent(Purchase purchase) {
        return Arrays.stream(NotificationTemplate.values())
                .filter(n -> n.getAllowed().contains(purchase.status()))
                .findFirst()
                .orElseThrow();
    }

}
