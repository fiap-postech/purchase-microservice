package br.com.fiap.tech.challenge.purchase.launcher.configuration;

import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseCreatedGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseReaderGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseWriterGateway;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.CreatePurchaseUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.FindAllPurchasesUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.FindPurchaseByUUIDUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.PostPurchaseToManufactureUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.PurchaseUseCaseFactory;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.UpdatePurchaseStatusUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public CreatePurchaseUseCase createPurchaseUseCase(PurchaseWriterGateway writerGateway) {
        return PurchaseUseCaseFactory.createPurchaseUseCase(writerGateway);
    }

    @Bean
    public PostPurchaseToManufactureUseCase postPurchaseToManufactureUseCase(PurchaseCreatedGateway gateway) {
        return PurchaseUseCaseFactory.postPurchaseToManufactureUseCase(gateway);
    }

    @Bean
    public UpdatePurchaseStatusUseCase updatePurchaseUseCase(FindPurchaseByUUIDUseCase findPurchaseUseCase, PurchaseWriterGateway gateway) {
        return PurchaseUseCaseFactory.updatePurchaseUseCase(findPurchaseUseCase, gateway);
    }

    @Bean
    public FindAllPurchasesUseCase findAllPurchasesUseCase(PurchaseReaderGateway gateway) {
        return PurchaseUseCaseFactory.findAllPurchasesUseCase(gateway);
    }

    @Bean
    public FindPurchaseByUUIDUseCase findPurchaseByUUIDUseCase(PurchaseReaderGateway gateway) {
        return PurchaseUseCaseFactory.findPurchaseByUUIDUseCase(gateway);
    }
}