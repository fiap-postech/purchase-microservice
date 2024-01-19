package br.com.fiap.tech.challenge.purchase.adapter.controller.purchase;

import br.com.fiap.tech.challenge.purchase.adapter.presenter.PurchasePresenter;
import br.com.fiap.tech.challenge.purchase.application.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.UpdatePurchaseStatusUseCase;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus;
import lombok.RequiredArgsConstructor;

import static java.util.UUID.fromString;

@RequiredArgsConstructor
class UpdatePurchaseStatusControllerImpl implements UpdatePurchaseStatusController {

    private final UpdatePurchaseStatusUseCase useCase;
    private final PurchasePresenter presenter;

    @Override
    public PurchaseDTO update(String purchaseUUID, PurchaseStatus status) {
        return presenter.present(useCase.update(fromString(purchaseUUID), status));
    }
}
