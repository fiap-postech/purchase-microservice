package br.com.fiap.tech.challenge.purchase.application.usecase.purchase;

import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseStatusGateway;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class PublishPurchaseStatusUseCaseImpl implements PublishPurchaseStatusUseCase {

    private final PurchaseStatusGateway gateway;

    @Override
    public void publish(Purchase purchase) {
        gateway.publish(purchase);
    }
}
