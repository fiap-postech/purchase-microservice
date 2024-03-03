package br.com.fiap.tech.challenge.purchase.launcher.configuration;

import br.com.fiap.tech.challenge.purchase.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.CustomerWriterGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseCreatedGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchasePaidGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseReaderGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseStatusGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseWriterGateway;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.CreatePurchaseUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.FindAllPurchasesUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.FindPurchaseByUUIDUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.PostPurchaseCreatedUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.PostPurchasePaidUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.PublishPurchaseStatusUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.PurchaseUseCaseFactory;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.RemoveCustomerDataUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.UpdatePaymentUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.UpdatePurchaseStatusUseCase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public CreatePurchaseUseCase createPurchaseUseCase(PurchaseWriterGateway writerGateway, PurchaseReaderGateway readerGateway) {
        return PurchaseUseCaseFactory.createPurchaseUseCase(writerGateway, readerGateway);
    }

    @Bean
    public UpdatePaymentUseCase updatePaymentUseCase(PurchaseReaderGateway readerGateway, PurchaseWriterGateway writerGateway) {
        return PurchaseUseCaseFactory.updatePaymentUseCase(readerGateway, writerGateway);
    }

    @Bean
    public PublishPurchaseStatusUseCase publishPurchaseStatusUseCase(PurchaseStatusGateway gateway) {
        return PurchaseUseCaseFactory.publishPurchaseStatusUseCase(gateway);
    }

    @Bean
    public PostPurchaseCreatedUseCase postPurchaseCreatedUseCase(PurchaseCreatedGateway gateway) {
        return PurchaseUseCaseFactory.postPurchaseCreatedUseCase(gateway);
    }

    @Bean
    public PostPurchasePaidUseCase postPurchasePaidUseCase(PurchasePaidGateway gateway) {
        return PurchaseUseCaseFactory.postPurchasePaidUseCase(gateway);
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

    @Bean
    public RemoveCustomerDataUseCase removeCustomerDataUseCase(@Qualifier("customerReaderGateway") CustomerReaderGateway readerGateway, @Qualifier("customerWriterGateway") CustomerWriterGateway writerGateway) {
        return PurchaseUseCaseFactory.removeCustomerDataUseCase(readerGateway, writerGateway);
    }
}