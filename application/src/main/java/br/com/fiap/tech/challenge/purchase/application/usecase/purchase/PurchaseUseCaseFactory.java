package br.com.fiap.tech.challenge.purchase.application.usecase.purchase;

import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseCreatedGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchasePaidGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseReaderGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseStatusGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseWriterGateway;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PurchaseUseCaseFactory {

    public static CreatePurchaseUseCase createPurchaseUseCase(PurchaseWriterGateway writerGateway, PurchaseReaderGateway readerGateway) {
        return new CreatePurchaseUseCaseImpl(readerGateway, writerGateway);
    }

    public static UpdatePaymentUseCase updatePaymentUseCase(PurchaseReaderGateway readerGateway, PurchaseWriterGateway writerGateway) {
        return new UpdatePaymentUseCaseImpl(readerGateway, writerGateway);
    }

    public static PublishPurchaseStatusUseCase publishPurchaseStatusUseCase(PurchaseStatusGateway gateway) {
        return new PublishPurchaseStatusUseCaseImpl(gateway);
    }

    public static PostPurchaseCreatedUseCase postPurchaseCreatedUseCase(PurchaseCreatedGateway gateway) {
        return new PostPurchaseCreatedUseCaseImpl(gateway);
    }

    public static PostPurchasePaidUseCase postPurchasePaidUseCase(PurchasePaidGateway gateway) {
        return new PostPurchasePaidUseCaseImpl(gateway);
    }

    public static UpdatePurchaseStatusUseCase updatePurchaseUseCase(FindPurchaseByUUIDUseCase findPurchaseUseCase, PurchaseWriterGateway gateway) {
        return new UpdatePurchaseStatusUseCaseImpl(findPurchaseUseCase, gateway);
    }

    public static FindAllPurchasesUseCase findAllPurchasesUseCase(PurchaseReaderGateway gateway) {
        return new FindAllPurchasesUseCaseImpl(gateway);
    }

    public static FindPurchaseByUUIDUseCase findPurchaseByUUIDUseCase(PurchaseReaderGateway gateway) {
        return new FindPurchaseByUUIDUseCaseImpl(gateway);
    }
}
