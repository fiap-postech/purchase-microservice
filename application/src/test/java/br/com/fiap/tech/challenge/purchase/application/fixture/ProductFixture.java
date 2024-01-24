package br.com.fiap.tech.challenge.purchase.application.fixture;

import br.com.fiap.tech.challenge.enterprise.valueobject.Price;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import java.math.BigDecimal;

import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;
import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductFixture {

    public static Model<Product> beverageModel() {
        return Instancio.of(Product.class)
                .set(field(Product::name), "Bebida")
                .set(field(Product::price), Price.of(makeMoney(BigDecimal.valueOf(5.00))))
                .toModel();
    }

    public static Model<Product> sideDishModel() {
        return Instancio.of(Product.class)
                .set(field(Product::name), "Acompanhamento")
                .set(field(Product::price), Price.of(makeMoney(BigDecimal.valueOf(3.00))))
                .toModel();
    }

    public static Model<Product> sandwichModel() {
        return Instancio.of(Product.class)
                .set(field(Product::name), "Lanche")
                .set(field(Product::price), Price.of(makeMoney(BigDecimal.valueOf(17.00))))
                .toModel();
    }
}
