package br.com.fiap.tech.challenge.purchase.adapter.controller.purchase;

import br.com.fiap.tech.challenge.purchase.application.dto.PurchaseDTO;

public interface FindPurchaseByUUIDController {
    PurchaseDTO get(String uuid);
}
