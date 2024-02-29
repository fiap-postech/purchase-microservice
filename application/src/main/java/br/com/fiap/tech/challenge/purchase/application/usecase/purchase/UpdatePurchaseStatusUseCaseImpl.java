package br.com.fiap.tech.challenge.purchase.application.usecase.purchase;

import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseWriterGateway;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
class UpdatePurchaseStatusUseCaseImpl implements UpdatePurchaseStatusUseCase {

    private final FindPurchaseByUUIDUseCase findPurchaseUseCase;
    private final PurchaseWriterGateway gateway;

    @Override
    public Purchase update(UUID uuid, PurchaseStatus status) {
        return update(findPurchaseUseCase.get(uuid), status);
    }

    @Override
    public Purchase update(Purchase purchase, PurchaseStatus status) {
        return gateway.write(updateStatus(purchase, status));
    }

    private Purchase updateStatus(Purchase purchase, PurchaseStatus status) {
        return switch (status) {
            case CREATED -> purchase.created();
            case WAITING_PAYMENT -> purchase.waitingPayment();
            case PAID_SUCCESS -> purchase.paidSuccessful();
            case PAID_ERROR -> purchase.paidFail();
            case WAITING_MAKE -> purchase.waitMake();
            case MAKING -> purchase.making();
            case MADE -> purchase.made();
            case DELIVERED -> purchase.delivered();
        };
    }
}
