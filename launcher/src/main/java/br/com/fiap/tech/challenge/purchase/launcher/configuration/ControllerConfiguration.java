package br.com.fiap.tech.challenge.purchase.launcher.configuration;

import br.com.fiap.tech.challenge.purchase.adapter.controller.purchase.CreatePurchaseController;
import br.com.fiap.tech.challenge.purchase.adapter.controller.purchase.FindAllPurchasesController;
import br.com.fiap.tech.challenge.purchase.adapter.controller.purchase.FindPurchaseByUUIDController;
import br.com.fiap.tech.challenge.purchase.adapter.controller.purchase.PurchaseControllerFactory;
import br.com.fiap.tech.challenge.purchase.adapter.controller.purchase.UpdatePurchaseStatusController;
import br.com.fiap.tech.challenge.purchase.adapter.presenter.PurchasePresenter;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.CreatePurchaseUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.FindAllPurchasesUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.FindPurchaseByUUIDUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.PostPurchaseToManufactureUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.UpdatePurchaseStatusUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfiguration {

    @Bean
    public CreatePurchaseController createPurchaseController(CreatePurchaseUseCase createUseCase, PostPurchaseToManufactureUseCase postToManufactureUseCase, PurchasePresenter presenter) {
        return PurchaseControllerFactory.createPurchaseController(createUseCase, postToManufactureUseCase, presenter);
    }

    @Bean
    public UpdatePurchaseStatusController updatePurchaseStatusController(UpdatePurchaseStatusUseCase useCase, PurchasePresenter presenter) {
        return PurchaseControllerFactory.updatePurchaseStatusController(useCase, presenter);
    }

    @Bean
    public FindPurchaseByUUIDController findPurchaseByUUIDController(FindPurchaseByUUIDUseCase useCase, PurchasePresenter presenter) {
        return PurchaseControllerFactory.findPurchaseByUUIDController(useCase, presenter);
    }

    @Bean
    public static FindAllPurchasesController findAllPurchasesController(FindAllPurchasesUseCase useCase, PurchasePresenter presenter) {
        return PurchaseControllerFactory.findAllPurchasesController(useCase, presenter);
    }
}
