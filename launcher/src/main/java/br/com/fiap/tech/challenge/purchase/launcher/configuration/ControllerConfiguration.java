package br.com.fiap.tech.challenge.purchase.launcher.configuration;

import br.com.fiap.tech.challenge.purchase.adapter.controller.purchase.CreatePurchaseController;
import br.com.fiap.tech.challenge.purchase.adapter.controller.purchase.FindAllPurchasesController;
import br.com.fiap.tech.challenge.purchase.adapter.controller.purchase.FindPurchaseByUUIDController;
import br.com.fiap.tech.challenge.purchase.adapter.controller.purchase.PurchaseControllerFactory;
import br.com.fiap.tech.challenge.purchase.adapter.controller.purchase.RemoveCustomerDataController;
import br.com.fiap.tech.challenge.purchase.adapter.controller.purchase.UpdatePaymentController;
import br.com.fiap.tech.challenge.purchase.adapter.controller.purchase.UpdatePurchaseStatusController;
import br.com.fiap.tech.challenge.purchase.adapter.presenter.PurchasePresenter;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.CreatePurchaseUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.FindAllPurchasesUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.FindPurchaseByUUIDUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.PostCustomerRemovedUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.PostPurchaseCreatedUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.PostPurchasePaidUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.PublishPurchaseStatusUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.RemoveCustomerDataUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.UpdatePaymentUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.UpdatePurchaseStatusUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfiguration {

    @Bean
    public CreatePurchaseController createPurchaseController(CreatePurchaseUseCase createUseCase, PostPurchaseCreatedUseCase postCreatedUseCase) {
        return PurchaseControllerFactory.createPurchaseController(createUseCase, postCreatedUseCase);
    }

    @Bean
    public UpdatePaymentController updatePaymentController(UpdatePaymentUseCase updatePaymentUseCase, PostPurchasePaidUseCase postPurchasePaidUseCase, PublishPurchaseStatusUseCase publishPurchaseStatusUseCase) {
        return PurchaseControllerFactory.updatePaymentController(updatePaymentUseCase, postPurchasePaidUseCase, publishPurchaseStatusUseCase);
    }

    @Bean
    public UpdatePurchaseStatusController updatePurchaseStatusController(UpdatePurchaseStatusUseCase updatePurchaseStatusUseCase, PublishPurchaseStatusUseCase publishPurchaseStatusUseCase, PurchasePresenter presenter) {
        return PurchaseControllerFactory.updatePurchaseStatusController(updatePurchaseStatusUseCase, publishPurchaseStatusUseCase, presenter);
    }

    @Bean
    public FindPurchaseByUUIDController findPurchaseByUUIDController(FindPurchaseByUUIDUseCase useCase, PurchasePresenter presenter) {
        return PurchaseControllerFactory.findPurchaseByUUIDController(useCase, presenter);
    }

    @Bean
    public FindAllPurchasesController findAllPurchasesController(FindAllPurchasesUseCase useCase, PurchasePresenter presenter) {
        return PurchaseControllerFactory.findAllPurchasesController(useCase, presenter);
    }

    @Bean
    public RemoveCustomerDataController removeCustomerDataController(RemoveCustomerDataUseCase removeUseCase, PostCustomerRemovedUseCase postRemovedUseCase) {
        return PurchaseControllerFactory.removeCustomerDataController(removeUseCase, postRemovedUseCase);
    }
}
