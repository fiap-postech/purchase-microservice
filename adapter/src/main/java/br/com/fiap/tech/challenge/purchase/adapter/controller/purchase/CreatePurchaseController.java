package br.com.fiap.tech.challenge.purchase.adapter.controller.purchase;

import br.com.fiap.tech.challenge.purchase.application.dto.CreatePurchaseDTO;
import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseDTO;

public interface CreatePurchaseController {

    PurchaseDTO create(CreatePurchaseDTO createPurchaseDTO);

}