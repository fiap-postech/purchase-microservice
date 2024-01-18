package br.com.fiap.tech.challenge.purchase.application.usecase.purchase;

import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseReaderGateway;
import br.com.fiap.tech.challenge.purchase.application.util.Page;
import br.com.fiap.tech.challenge.purchase.application.util.ResponseList;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class FindAllPurchasesUseCaseImpl implements FindAllPurchasesUseCase {

    private final PurchaseReaderGateway gateway;

    @Override
    public ResponseList<Purchase> list(Page page) {
        return gateway.readAll(page);
    }
}
