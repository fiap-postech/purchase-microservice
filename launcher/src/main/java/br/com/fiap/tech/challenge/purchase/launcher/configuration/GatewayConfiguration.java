package br.com.fiap.tech.challenge.purchase.launcher.configuration;

import br.com.fiap.tech.challenge.purchase.adapter.gateway.purchase.PurchaseGatewayFactory;
import br.com.fiap.tech.challenge.purchase.adapter.repository.CustomerReaderRepository;
import br.com.fiap.tech.challenge.purchase.adapter.repository.CustomerRemovedRepository;
import br.com.fiap.tech.challenge.purchase.adapter.repository.CustomerWriterRepository;
import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchaseCreatedRepository;
import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchasePaidRepository;
import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchaseReaderRepository;
import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchaseStatusRepository;
import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchaseWriterRepository;
import br.com.fiap.tech.challenge.purchase.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.CustomerRemovedGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.CustomerWriterGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseCreatedGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchasePaidGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseReaderGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseStatusGateway;
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
    public PurchasePaidGateway purchasePaidGateway(PurchasePaidRepository repository) {
        return PurchaseGatewayFactory.purchasePaidGateway(repository);
    }

    @Bean
    public PurchaseStatusGateway purchaseStatusGateway(PurchaseStatusRepository repository) {
        return PurchaseGatewayFactory.purchaseStatusGateway(repository);
    }

    @Bean("customerReaderGateway")
    public CustomerReaderGateway customerReaderGateway(CustomerReaderRepository readerRepository, CustomerWriterRepository writerRepository) {
        return PurchaseGatewayFactory.customerReaderGateway(readerRepository, writerRepository);
    }

    @Bean("customerWriterGateway")
    public CustomerWriterGateway customerWriterGateway(CustomerReaderRepository readerRepository, CustomerWriterRepository writerRepository) {
        return PurchaseGatewayFactory.customerWriterGateway(readerRepository, writerRepository);
    }

    @Bean
    public CustomerRemovedGateway customerRemovedGateway(CustomerRemovedRepository repository) {
        return PurchaseGatewayFactory.customerRemovedGateway(repository);
    }
}
