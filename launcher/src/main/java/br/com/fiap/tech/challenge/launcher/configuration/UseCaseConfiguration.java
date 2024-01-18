package br.com.fiap.tech.challenge.launcher.configuration;

import br.com.fiap.tech.challenge.application.gateway.CartReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.CartWriterGateway;
import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.CustomerWriterGateway;
import br.com.fiap.tech.challenge.application.gateway.PaymentGateway;
import br.com.fiap.tech.challenge.application.gateway.ProductReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.ProductWriterGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseReaderGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseWriterGateway;
import br.com.fiap.tech.challenge.application.usecase.cart.AddCartItemUseCase;
import br.com.fiap.tech.challenge.application.usecase.cart.CartUseCaseFactory;
import br.com.fiap.tech.challenge.application.usecase.cart.CreateCartUseCase;
import br.com.fiap.tech.challenge.application.usecase.cart.FindCartByUUIDUseCase;
import br.com.fiap.tech.challenge.application.usecase.cart.RemoveCartItemUseCase;
import br.com.fiap.tech.challenge.application.usecase.cart.UpdateCartItemUseCase;
import br.com.fiap.tech.challenge.application.usecase.customer.CreateCustomerUseCase;
import br.com.fiap.tech.challenge.application.usecase.customer.CustomerUseCaseFactory;
import br.com.fiap.tech.challenge.application.usecase.customer.FindCustomerByDocumentUseCase;
import br.com.fiap.tech.challenge.application.usecase.customer.FindCustomerByUUIDUseCase;
import br.com.fiap.tech.challenge.application.usecase.customer.UpgradeCustomerUseCase;
import br.com.fiap.tech.challenge.application.usecase.product.CreateProductUseCase;
import br.com.fiap.tech.challenge.application.usecase.product.DisableProductUseCase;
import br.com.fiap.tech.challenge.application.usecase.product.EnableProductUseCase;
import br.com.fiap.tech.challenge.application.usecase.product.FindAllAvailableProductByCategoryUseCase;
import br.com.fiap.tech.challenge.application.usecase.product.FindAllAvailableProductUseCase;
import br.com.fiap.tech.challenge.application.usecase.product.FindProductByUUIDUseCase;
import br.com.fiap.tech.challenge.application.usecase.product.ProductUseCaseFactory;
import br.com.fiap.tech.challenge.application.usecase.product.UpdateProductUseCase;
import br.com.fiap.tech.challenge.application.usecase.purchase.CheckoutUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.CreatePurchaseUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.FindAllPurchasesUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.FindPurchaseByPaymentIdUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.FindPurchaseByUUIDUseCase;
import br.com.fiap.tech.challenge.application.usecase.purchase.PaymentConfirmUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.PurchaseUseCaseFactory;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.UpdatePurchaseStatusUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {
    @Bean
    public FindAllAvailableProductUseCase findAllAvailableProductService(ProductReaderGateway reader) {
        return ProductUseCaseFactory.findAllAvailableProductService(reader);
    }

    @Bean
    public FindAllAvailableProductByCategoryUseCase findAllAvailableProductByCategory(ProductReaderGateway reader) {
        return ProductUseCaseFactory.findAllAvailableProductByCategory(reader);
    }

    @Bean
    public FindProductByUUIDUseCase findProductByUUIDService(ProductReaderGateway reader) {
        return ProductUseCaseFactory.findProductByUUIDService(reader);
    }

    @Bean
    public CreateProductUseCase createProductService(ProductWriterGateway writerGateway, ProductReaderGateway readerGateway) {
        return ProductUseCaseFactory.createProductService(writerGateway, readerGateway);
    }

    @Bean
    public UpdateProductUseCase updateProductService(ProductWriterGateway writer, ProductReaderGateway reader) {
        return ProductUseCaseFactory.updateProductService(reader, writer);
    }

    @Bean
    public EnableProductUseCase enableProductService(ProductWriterGateway writer, ProductReaderGateway reader) {
        return ProductUseCaseFactory.enableProductService(writer, reader);
    }

    @Bean
    public DisableProductUseCase disableProductService(ProductWriterGateway writer, ProductReaderGateway reader) {
        return ProductUseCaseFactory.disableProductService(writer, reader);
    }

    @Bean
    public CreateCustomerUseCase createCustomerService(CustomerWriterGateway writer, CustomerReaderGateway reader) {
        return CustomerUseCaseFactory.createCustomerService(writer, reader);
    }

    @Bean
    public UpgradeCustomerUseCase upgradeCustomerService(CustomerWriterGateway writer, CustomerReaderGateway reader) {
        return CustomerUseCaseFactory.upgradeCustomerService(writer, reader);
    }

    @Bean
    public FindCustomerByDocumentUseCase findCustomerByDocumentService(CustomerReaderGateway reader) {
        return CustomerUseCaseFactory.findCustomerByDocumentService(reader);
    }

    @Bean
    public FindCustomerByUUIDUseCase findCustomerByUUIDService(CustomerReaderGateway reader) {
        return CustomerUseCaseFactory.findFindCustomerByUUIDService(reader);
    }

    @Bean
    public FindCartByUUIDUseCase findCartByUUIDService(CartReaderGateway reader) {
        return CartUseCaseFactory.findCartByUUIDService(reader);
    }

    @Bean
    public CreateCartUseCase createCartService(FindCustomerByUUIDUseCase useCase, CartWriterGateway writer) {
        return CartUseCaseFactory.createCartService(useCase, writer);
    }

    @Bean
    public AddCartItemUseCase addCartItemService(CartReaderGateway reader, CartWriterGateway writer, FindProductByUUIDUseCase findProductByUUIDUseCase) {
        return CartUseCaseFactory.addCartItemService(reader, writer, findProductByUUIDUseCase);
    }

    @Bean
    public UpdateCartItemUseCase updateCartItemService(CartReaderGateway reader, CartWriterGateway writer, FindProductByUUIDUseCase findProductByUUIDUseCase) {
        return CartUseCaseFactory.updateCartItemService(reader, writer, findProductByUUIDUseCase);
    }

    @Bean
    public RemoveCartItemUseCase removeCartItemUseCase(CartReaderGateway useCase, CartWriterGateway presenter) {
        return CartUseCaseFactory.removeCartItemUseCase(useCase, presenter);
    }

    @Bean
    public CheckoutUseCase checkoutUseCase(FindCartByUUIDUseCase cartFinderUseCase, CreatePurchaseUseCase createPurchaseUseCase, PaymentGateway paymentGateway) {
        return PurchaseUseCaseFactory.checkoutUseCase(cartFinderUseCase, createPurchaseUseCase, paymentGateway);
    }

    @Bean
    public CreatePurchaseUseCase createPurchaseUseCase(PurchaseWriterGateway writer) {
        return PurchaseUseCaseFactory.createPurchaseUseCase(writer);
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
    public FindPurchaseByPaymentIdUseCase findPurchaseByPaymentIdUseCase(PaymentGateway paymentGateway, PurchaseReaderGateway purchaseReaderGateway) {
        return PurchaseUseCaseFactory.findPurchaseByPaymentIdUseCase(paymentGateway, purchaseReaderGateway);
    }

    @Bean
    public FindPurchaseByUUIDUseCase findPurchaseByUUIDUseCase(PurchaseReaderGateway gateway) {
        return PurchaseUseCaseFactory.findPurchaseByUUIDUseCase(gateway);
    }

    @Bean
    public PaymentConfirmUseCase paymentConfirmUseCase(FindPurchaseByPaymentIdUseCase findPurchaseUseCase, UpdatePurchaseStatusUseCase updatePurchaseStatusUseCase) {
        return PurchaseUseCaseFactory.paymentConfirmUseCase(findPurchaseUseCase, updatePurchaseStatusUseCase);
    }
}