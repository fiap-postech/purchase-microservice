package br.com.fiap.tech.challenge.purchase.adapter.presenter;

import br.com.fiap.tech.challenge.purchase.application.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.purchase.application.util.ResponseList;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;

public interface PurchasePresenter {

    PurchaseDTO present(Purchase purchase);

    ResponseList<PurchaseDTO> present(ResponseList<Purchase> list);

}
