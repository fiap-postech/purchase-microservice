package br.com.fiap.tech.challenge.purchase.application.usecase.purchase;

import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseReaderGateway;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
class FindPurchaseByUUIDUseCaseImpl implements FindPurchaseByUUIDUseCase {

    private final PurchaseReaderGateway gateway;

    @Override
    public Purchase get(UUID uuid) {
        return gateway.readById(uuid);
    }
}
