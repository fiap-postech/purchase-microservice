package br.com.fiap.tech.challenge.purchase.adapter.presenter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PresenterFactory {

    public static ProductPresenter productPresenter(){
        return new ProductPresenterImpl();
    }

    public static CustomerPresenter customerPresenter() {
        return new CustomerPresenterImpl();
    }

    public static CartPresenter cartPresenter() {
        return new CartPresenterImpl();
    }

    public static PurchasePresenter purchasePresenter() {
        return new PurchasePresenterImpl();
    }
}
