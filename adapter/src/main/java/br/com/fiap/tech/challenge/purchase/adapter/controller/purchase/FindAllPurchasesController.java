package br.com.fiap.tech.challenge.purchase.adapter.controller.purchase;

import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.purchase.application.util.Page;
import br.com.fiap.tech.challenge.purchase.application.util.ResponseList;

public interface FindAllPurchasesController {

    ResponseList<PurchaseDTO> list(Page page);
}
