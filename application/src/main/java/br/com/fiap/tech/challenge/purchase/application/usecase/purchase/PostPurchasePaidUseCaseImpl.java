package br.com.fiap.tech.challenge.purchase.application.usecase.purchase;

import br.com.fiap.tech.challenge.purchase.application.gateway.PurchasePaidGateway;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class PostPurchasePaidUseCaseImpl implements PostPurchasePaidUseCase {

    private final PurchasePaidGateway gateway;

    @Override
    public void post(Purchase purchase) {
        gateway.notify(purchase);
    }
}
