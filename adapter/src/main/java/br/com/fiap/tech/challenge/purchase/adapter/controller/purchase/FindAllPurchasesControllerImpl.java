package br.com.fiap.tech.challenge.purchase.adapter.controller.purchase;

import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.purchase.adapter.presenter.PurchasePresenter;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.FindAllPurchasesUseCase;
import br.com.fiap.tech.challenge.purchase.application.util.Page;
import br.com.fiap.tech.challenge.purchase.application.util.ResponseList;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class FindAllPurchasesControllerImpl implements FindAllPurchasesController {

    private final FindAllPurchasesUseCase useCase;
    private final PurchasePresenter presenter;

    @Override
    public ResponseList<PurchaseDTO> list(Page page) {
        return presenter.present(useCase.list(page));
    }
}
