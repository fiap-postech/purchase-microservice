package br.com.fiap.tech.challenge.purchase.application.fixture;

import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import java.util.List;

import static br.com.fiap.tech.challenge.purchase.application.fixture.CustomerFixture.customerModel;
import static br.com.fiap.tech.challenge.purchase.application.fixture.PaymentFixture.paymentModel;
import static br.com.fiap.tech.challenge.purchase.application.fixture.PurchaseItemFixture.singleBeverageItem;
import static br.com.fiap.tech.challenge.purchase.application.fixture.PurchaseItemFixture.singleSandwichItem;
import static br.com.fiap.tech.challenge.purchase.application.fixture.PurchaseItemFixture.singleSideDishItem;
import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PurchaseFixture {

    public static Model<Purchase> purchaseModel() {
        return purchaseModel(PurchaseStatus.CREATED);
    }

    public static Model<Purchase> purchaseModel(PurchaseStatus status) {
        return Instancio.of(Purchase.class)
                .set(field(Purchase::customer), Instancio.of(customerModel()).create())
                .set(field(Purchase::items), List.of(
                        Instancio.of(singleSandwichItem()).create(),
                        Instancio.of(singleBeverageItem()).create(),
                        Instancio.of(singleSideDishItem()).create()
                ))
                .set(field(Purchase::status), status)
                .set(field(Purchase::payment), Instancio.of(paymentModel()).create())
                .generate(field(Purchase::date), gen -> gen.temporal().localDate())
                .toModel();
    }
}
