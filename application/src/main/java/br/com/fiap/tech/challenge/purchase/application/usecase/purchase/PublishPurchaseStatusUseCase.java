package br.com.fiap.tech.challenge.purchase.application.usecase.purchase;

import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;

public interface PublishPurchaseStatusUseCase {

    void publish(Purchase purchase);

}
