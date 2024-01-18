package br.com.fiap.tech.challenge.purchase.adapter.presenter;

import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.purchase.application.util.ResponseList;

public interface PurchasePresenter {

    PurchaseDTO present(Purchase purchase);

    ResponseList<PurchaseDTO> present(ResponseList<Purchase> list);

}
