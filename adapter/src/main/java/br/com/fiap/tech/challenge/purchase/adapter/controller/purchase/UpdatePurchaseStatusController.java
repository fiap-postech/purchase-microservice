package br.com.fiap.tech.challenge.purchase.adapter.controller.purchase;

import br.com.fiap.tech.challenge.purchase.application.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus;

public interface UpdatePurchaseStatusController {

    PurchaseDTO update(String purchaseUUID, PurchaseStatus status);
}
