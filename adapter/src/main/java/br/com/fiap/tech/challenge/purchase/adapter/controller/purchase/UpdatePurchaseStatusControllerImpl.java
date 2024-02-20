package br.com.fiap.tech.challenge.purchase.adapter.controller.purchase;

import br.com.fiap.tech.challenge.purchase.adapter.presenter.PurchasePresenter;
import br.com.fiap.tech.challenge.purchase.application.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.PublishPurchaseStatusUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.UpdatePurchaseStatusUseCase;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import static java.util.UUID.fromString;

@RequiredArgsConstructor
class UpdatePurchaseStatusControllerImpl implements UpdatePurchaseStatusController {

    private final UpdatePurchaseStatusUseCase updatePurchaseStatusUseCase;
    private final PublishPurchaseStatusUseCase publishPurchaseStatusUseCase;
    private final PurchasePresenter presenter;

    @Override
    @Transactional
    public PurchaseDTO update(String purchaseUUID, PurchaseStatus status) {
        var purchase = updatePurchaseStatusUseCase.update(fromString(purchaseUUID), status);

        publishPurchaseStatusUseCase.publish(purchase);

        return presenter.present(purchase);
    }
}
