package br.com.fiap.tech.challenge.purchase.adapter.controller.purchase;

import br.com.fiap.tech.challenge.purchase.adapter.presenter.PurchasePresenter;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.CreatePurchaseUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.FindAllPurchasesUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.FindPurchaseByUUIDUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.PostPurchaseToManufactureUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.UpdatePurchaseStatusUseCase;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PurchaseControllerFactory {

    public static CreatePurchaseController createPurchaseController(CreatePurchaseUseCase createUseCase, PostPurchaseToManufactureUseCase postToManufactureUseCase,  PurchasePresenter presenter) {
        return new CreatePurchaseControllerImpl(createUseCase, postToManufactureUseCase, presenter);
    }

    public static UpdatePurchaseStatusController updatePurchaseStatusController(UpdatePurchaseStatusUseCase useCase, PurchasePresenter presenter) {
        return new UpdatePurchaseStatusControllerImpl(useCase, presenter);
    }

    public static FindPurchaseByUUIDController findPurchaseByUUIDController(FindPurchaseByUUIDUseCase useCase, PurchasePresenter presenter) {
        return new FindPurchaseByUUIDControllerImpl(useCase, presenter);
    }

    public static FindAllPurchasesController findAllPurchasesController(FindAllPurchasesUseCase useCase, PurchasePresenter presenter) {
        return new FindAllPurchasesControllerImpl(useCase, presenter);
    }
}
