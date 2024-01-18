package br.com.fiap.tech.challenge.purchase.adapter.controller.purchase;

import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseDTO;

public interface FindPurchaseByUUIDController {
    PurchaseDTO get(String uuid);
}
