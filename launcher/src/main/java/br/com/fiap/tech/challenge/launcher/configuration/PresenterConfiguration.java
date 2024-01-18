package br.com.fiap.tech.challenge.launcher.configuration;

import br.com.fiap.tech.challenge.adapter.presenter.CartPresenter;
import br.com.fiap.tech.challenge.adapter.presenter.CustomerPresenter;
import br.com.fiap.tech.challenge.purchase.adapter.presenter.PresenterFactory;
import br.com.fiap.tech.challenge.adapter.presenter.ProductPresenter;
import br.com.fiap.tech.challenge.purchase.adapter.presenter.PurchasePresenter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PresenterConfiguration {

    @Bean
    public ProductPresenter productPresenter(){
        return PresenterFactory.productPresenter();
    }

    @Bean
    public CustomerPresenter customerPresenter() {
        return PresenterFactory.customerPresenter();
    }

    @Bean
    public CartPresenter cartPresenter() {
        return PresenterFactory.cartPresenter();
    }

    @Bean
    public PurchasePresenter purchasePresenter() {
        return PresenterFactory.purchasePresenter();
    }

}
