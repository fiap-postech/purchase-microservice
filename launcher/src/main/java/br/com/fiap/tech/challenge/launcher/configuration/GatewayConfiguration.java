package br.com.fiap.tech.challenge.launcher.configuration;

import br.com.fiap.tech.challenge.adapter.gateway.cart.CartGatewayFactory;
import br.com.fiap.tech.challenge.adapter.gateway.customer.CustomerGatewayFactory;
import br.com.fiap.tech.challenge.adapter.gateway.product.ProductGatewayFactory;
import br.com.fiap.tech.challenge.purchase.adapter.gateway.purchase.PurchaseGatewayFactory;
import br.com.fiap.tech.challenge.adapter.repository.CartReaderRepository;
import br.com.fiap.tech.challenge.adapter.repository.CartWriterRepository;
import br.com.fiap.tech.challenge.adapter.repository.CustomerReaderRepository;
import br.com.fiap.tech.challenge.adapter.repository.CustomerWriterRepository;
import br.com.fiap.tech.challenge.adapter.repository.ProductReaderRepository;
import br.com.fiap.tech.challenge.adapter.repository.ProductWriterRepository;
import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchaseReaderRepository;
import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchaseWriterRepository;
import br.com.fiap.tech.challenge.application.gateway.CartReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.CartWriterGateway;
import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.CustomerWriterGateway;
import br.com.fiap.tech.challenge.application.gateway.ProductReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.ProductWriterGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseReaderGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseWriterGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    public ProductReaderGateway productReaderGateway(ProductReaderRepository repository) {
        return ProductGatewayFactory.productReaderGateway(repository);
    }

    @Bean
    public ProductWriterGateway productWriterGateway(ProductWriterRepository repository) {
        return ProductGatewayFactory.productWriterGateway(repository);
    }

    @Bean
    public CustomerWriterGateway customerWriterGateway(CustomerWriterRepository writerRepository) {
        return CustomerGatewayFactory.customerWriterGateway(writerRepository);
    }

    @Bean
    public CustomerReaderGateway customerReaderGateway(CustomerReaderRepository repository) {
        return CustomerGatewayFactory.customerReaderGateway(repository);
    }

    @Bean
    public CartWriterGateway cartWriterGateway(CartWriterRepository repository) {
        return CartGatewayFactory.cartWriterGateway(repository);
    }

    @Bean
    public CartReaderGateway cartReaderGateway(CartReaderRepository repository) {
        return CartGatewayFactory.cartReaderGateway(repository);
    }

    @Bean
    public PurchaseReaderGateway purchaseReaderGateway(PurchaseReaderRepository repository) {
        return PurchaseGatewayFactory.purchaseReaderGateway(repository);
    }

    @Bean
    public PurchaseWriterGateway purchaseWriterGateway(PurchaseWriterRepository repository) {
        return PurchaseGatewayFactory.purchaseWriterGateway(repository);
    }

}
