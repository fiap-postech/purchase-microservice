package br.com.fiap.tech.challenge.purchase.launcher.fixture.input;

import br.com.fiap.tech.challenge.purchase.adapter.dto.ComboInputDTO;
import br.com.fiap.tech.challenge.purchase.adapter.dto.ProductInputDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import java.math.BigDecimal;

import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductInputDTOFixture {

    public static Model<ProductInputDTO> beverageInputDTO() {
        return Instancio.of(ProductInputDTO.class)
                .generate(field(ProductInputDTO::getId), gen -> gen.text().uuid())
                .set(field(ProductInputDTO::getName), "Bebida")
                .set(field(ProductInputDTO::getPrice), BigDecimal.valueOf(5.00))
                .set(field(ProductInputDTO::getFullPrice), BigDecimal.valueOf(5.00))
                .set(field(ProductInputDTO::getDiscount), BigDecimal.ZERO)
                .toModel();
    }

    public static Model<ProductInputDTO> sideDishInputDTO() {
        return Instancio.of(ProductInputDTO.class)
                .generate(field(ProductInputDTO::getId), gen -> gen.text().uuid())
                .set(field(ProductInputDTO::getName), "Acompanhamento")
                .set(field(ProductInputDTO::getPrice), BigDecimal.valueOf(3.00))
                .set(field(ProductInputDTO::getFullPrice), BigDecimal.valueOf(3.00))
                .set(field(ProductInputDTO::getDiscount), BigDecimal.ZERO)
                .toModel();
    }

    public static Model<ProductInputDTO> sandwichInputDTO() {
        return Instancio.of(ProductInputDTO.class)
                .generate(field(ProductInputDTO::getId), gen -> gen.text().uuid())
                .set(field(ProductInputDTO::getName), "Lanche")
                .set(field(ProductInputDTO::getPrice), BigDecimal.valueOf(17.00))
                .set(field(ProductInputDTO::getFullPrice), BigDecimal.valueOf(17.00))
                .set(field(ProductInputDTO::getDiscount), BigDecimal.ZERO)
                .toModel();
    }

    public static Model<ComboInputDTO> comboInputDTO() {
        return Instancio.of(ComboInputDTO.class)
                .generate(field(ComboInputDTO::getId), gen -> gen.text().uuid())
                .set(field(ComboInputDTO::getName), "Combo")
                .set(field(ComboInputDTO::getPrice), BigDecimal.valueOf(20.00))
                .set(field(ComboInputDTO::getFullPrice), BigDecimal.valueOf(25.00))
                .set(field(ComboInputDTO::getDiscount), BigDecimal.valueOf(5.00))
                .set(field(ComboInputDTO::getBeverage), Instancio.create(beverageInputDTO()))
                .set(field(ComboInputDTO::getSideDish), Instancio.create(sideDishInputDTO()))
                .set(field(ComboInputDTO::getSandwich), Instancio.create(sandwichInputDTO()))
                .toModel();
    }

}
