package br.com.fiap.tech.challenge.purchase.application.gateway;

import br.com.fiap.tech.challenge.purchase.application.dto.SimplePurchaseDTO;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;

public interface PurchaseCreatedGateway {

    void notify(Purchase purchase);

}
