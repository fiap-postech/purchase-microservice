package br.com.fiap.tech.challenge.purchase.adapter.gateway.purchase;

import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchaseCreatedRepository;
import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchaseReaderRepository;
import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchaseWriterRepository;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseCreatedGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseReaderGateway;
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

}
