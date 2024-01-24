package br.com.fiap.tech.challenge.purchase.application.fixture;

import br.com.fiap.tech.challenge.enterprise.valueobject.Discount;
import br.com.fiap.tech.challenge.enterprise.valueobject.Price;
import br.com.fiap.tech.challenge.enterprise.valueobject.Quantity;
import br.com.fiap.tech.challenge.purchase.enterprise.valueobject.PurchaseItem;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import java.math.BigDecimal;

import static br.com.fiap.tech.challenge.purchase.application.fixture.ProductFixture.beverageModel;
import static br.com.fiap.tech.challenge.purchase.application.fixture.ProductFixture.sandwichModel;
import static br.com.fiap.tech.challenge.purchase.application.fixture.ProductFixture.sideDishModel;
import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;
import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PurchaseItemFixture {

    public static Model<PurchaseItem> singleBeverageItem() {
        return Instancio.of(PurchaseItem.class)
                .set(field(PurchaseItem::product), Instancio.create(beverageModel()))
                .set(field(PurchaseItem::price), Price.of(makeMoney(BigDecimal.valueOf(5.00))))
                .set(field(PurchaseItem::discount), Discount.withoutDiscount())
                .set(field(PurchaseItem::quantity), Quantity.of(1))
                .toModel();
    }

    public static Model<PurchaseItem> singleSideDishItem() {
        return Instancio.of(PurchaseItem.class)
                .set(field(PurchaseItem::product), Instancio.create(sideDishModel()))
                .set(field(PurchaseItem::price), Price.of(makeMoney(BigDecimal.valueOf(3.00))))
                .set(field(PurchaseItem::discount), Discount.withoutDiscount())
                .set(field(PurchaseItem::quantity), Quantity.of(1))
                .toModel();
    }

    public static Model<PurchaseItem> singleSandwichItem() {
        return Instancio.of(PurchaseItem.class)
                .set(field(PurchaseItem::product), Instancio.create(sandwichModel()))
                .set(field(PurchaseItem::price), Price.of(makeMoney(BigDecimal.valueOf(17.00))))
                .set(field(PurchaseItem::discount), Discount.withoutDiscount())
                .set(field(PurchaseItem::quantity), Quantity.of(1))
                .toModel();
    }
}
