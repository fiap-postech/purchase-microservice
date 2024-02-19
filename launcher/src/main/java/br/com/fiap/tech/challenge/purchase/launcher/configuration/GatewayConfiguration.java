package br.com.fiap.tech.challenge.purchase.launcher.configuration;

import br.com.fiap.tech.challenge.purchase.adapter.gateway.purchase.PurchaseGatewayFactory;
import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchaseCreatedRepository;
import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchaseReaderRepository;
import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchaseWriterRepository;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseCreatedGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchasePaidGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseReaderGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseWriterGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    public PurchaseReaderGateway purchaseReaderGateway(PurchaseReaderRepository repository) {
        return PurchaseGatewayFactory.purchaseReaderGateway(repository);
    }

    @Bean
    public PurchaseWriterGateway purchaseWriterGateway(PurchaseWriterRepository repository) {
        return PurchaseGatewayFactory.purchaseWriterGateway(repository);
    }

    @Bean
    public PurchaseCreatedGateway purchaseCreatedGateway(PurchaseCreatedRepository repository) {
        return PurchaseGatewayFactory.purchaseCreatedGateway(repository);
    }

    @Bean
    public PurchasePaidGateway purchasePaidGateway() {
        return PurchaseGatewayFactory.purchasePaidGateway();
    }

}
