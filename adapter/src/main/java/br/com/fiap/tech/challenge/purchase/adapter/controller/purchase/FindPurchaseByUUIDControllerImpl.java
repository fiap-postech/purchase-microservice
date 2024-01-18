package br.com.fiap.tech.challenge.purchase.adapter.controller.purchase;

import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.purchase.adapter.presenter.PurchasePresenter;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.FindPurchaseByUUIDUseCase;
import lombok.RequiredArgsConstructor;

import static java.util.UUID.fromString;

@RequiredArgsConstructor
class FindPurchaseByUUIDControllerImpl implements FindPurchaseByUUIDController {

    private final FindPurchaseByUUIDUseCase useCase;
    private final PurchasePresenter presenter;

    @Override
    public PurchaseDTO get(String uuid) {
        return presenter.present(useCase.get(fromString(uuid)));
    }
}
