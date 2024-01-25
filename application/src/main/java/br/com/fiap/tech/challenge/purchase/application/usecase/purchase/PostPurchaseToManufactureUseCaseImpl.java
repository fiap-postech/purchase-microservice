package br.com.fiap.tech.challenge.purchase.application.usecase.purchase;

import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseCreatedGateway;
import br.com.fiap.tech.challenge.purchase.application.mapper.PurchaseMapper;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
class PostPurchaseToManufactureUseCaseImpl implements PostPurchaseToManufactureUseCase {

    private final PurchaseCreatedGateway gateway;

    @Override
    public void post(Purchase purchase) {
        gateway.notify(purchase);
    }
}
