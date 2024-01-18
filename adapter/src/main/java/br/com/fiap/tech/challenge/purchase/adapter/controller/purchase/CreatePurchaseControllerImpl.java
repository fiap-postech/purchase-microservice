package br.com.fiap.tech.challenge.purchase.adapter.controller.purchase;

import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.purchase.adapter.presenter.PurchasePresenter;
import br.com.fiap.tech.challenge.purchase.application.dto.CreatePurchaseDTO;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.CreatePurchaseUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class CreatePurchaseControllerImpl implements CreatePurchaseController {

    private final CreatePurchaseUseCase useCase;
    private final PurchasePresenter presenter;

    @Override
    public PurchaseDTO create(CreatePurchaseDTO createPurchaseDTO) {
        return presenter.present(useCase.create(createPurchaseDTO));
    }
}
