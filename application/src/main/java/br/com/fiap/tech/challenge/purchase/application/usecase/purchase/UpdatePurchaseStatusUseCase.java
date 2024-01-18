package br.com.fiap.tech.challenge.purchase.application.usecase.purchase;

import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus;

import java.util.UUID;

public interface UpdatePurchaseStatusUseCase {

    Purchase update(UUID purchaseUUID, PurchaseStatus status);
    Purchase update(Purchase purchase, PurchaseStatus status);
}
