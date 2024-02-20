package br.com.fiap.tech.challenge.purchase.adapter.gateway.purchase;

import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchaseCreatedRepository;
import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchasePaidRepository;
import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchaseReaderRepository;
import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchaseStatusRepository;
import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchaseWriterRepository;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseCreatedGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchasePaidGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseReaderGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseStatusGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseWriterGateway;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PurchaseGatewayFactory {

    public static PurchaseReaderGateway purchaseReaderGateway(PurchaseReaderRepository repository) {
        return new PurchaseReaderGatewayImpl(repository);
    }

    public static PurchaseWriterGateway purchaseWriterGateway(PurchaseWriterRepository repository) {
        return new PurchaseWriterGatewayImpl(repository);
    }

    public static PurchaseCreatedGateway purchaseCreatedGateway(PurchaseCreatedRepository repository) {
        return new PurchaseCreatedGatewayImpl(repository);
    }

    public static PurchasePaidGateway purchasePaidGateway(PurchasePaidRepository repository) {
        return new PurchasePaidGatewayImpl(repository);
    }

    public static PurchaseStatusGateway purchaseStatusGateway(PurchaseStatusRepository repository) {
        return new PurchaseStatusGatewayImpl(repository);
    }

}
