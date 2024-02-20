package br.com.fiap.tech.challenge.purchase.adapter.controller.purchase;

import br.com.fiap.tech.challenge.purchase.adapter.presenter.PurchasePresenter;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.CreatePurchaseUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.FindAllPurchasesUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.FindPurchaseByUUIDUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.PostPurchaseCreatedUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.PostPurchasePaidUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.PublishPurchaseStatusUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.UpdatePaymentUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.UpdatePurchaseStatusUseCase;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PurchaseControllerFactory {

    public static CreatePurchaseController createPurchaseController(CreatePurchaseUseCase createUseCase, PostPurchaseCreatedUseCase postCreatedUseCase) {
        return new CreatePurchaseControllerImpl(createUseCase, postCreatedUseCase);
    }

    public static UpdatePaymentController updatePaymentController(UpdatePaymentUseCase updatePaymentUseCase, PostPurchasePaidUseCase postPurchasePaidUseCase, PublishPurchaseStatusUseCase publishPurchaseStatusUseCase) {
        return new UpdatePaymentControllerImpl(updatePaymentUseCase, postPurchasePaidUseCase, publishPurchaseStatusUseCase);
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
