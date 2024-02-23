package br.com.fiap.tech.challenge.purchase.launcher.fixture.input;

import br.com.fiap.tech.challenge.purchase.adapter.dto.ComboProductInputDTO;
import br.com.fiap.tech.challenge.purchase.adapter.dto.SingleProductInputDTO;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.ProductCategory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import java.math.BigDecimal;

import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductInputDTOFixture {

    public static Model<SingleProductInputDTO> beverageInputDTO() {
        return Instancio.of(SingleProductInputDTO.class)
                .generate(field(SingleProductInputDTO::getId), gen -> gen.text().uuid())
                .set(field(SingleProductInputDTO::getName), "Bebida")
                .set(field(SingleProductInputDTO::getPrice), BigDecimal.valueOf(5.00))
                .set(field(SingleProductInputDTO::getFullPrice), BigDecimal.valueOf(5.00))
                .set(field(SingleProductInputDTO::getDiscount), BigDecimal.ZERO)
                .set(field(SingleProductInputDTO::getCategory), ProductCategory.BEVERAGE)
                .toModel();
    }

    public static Model<SingleProductInputDTO> sideDishInputDTO() {
        return Instancio.of(SingleProductInputDTO.class)
                .generate(field(SingleProductInputDTO::getId), gen -> gen.text().uuid())
                .set(field(SingleProductInputDTO::getName), "Acompanhamento")
                .set(field(SingleProductInputDTO::getPrice), BigDecimal.valueOf(3.00))
                .set(field(SingleProductInputDTO::getFullPrice), BigDecimal.valueOf(3.00))
                .set(field(SingleProductInputDTO::getDiscount), BigDecimal.ZERO)
                .set(field(SingleProductInputDTO::getCategory), ProductCategory.SIDE_DISH)
                .toModel();
    }

    public static Model<SingleProductInputDTO> sandwichInputDTO() {
        return Instancio.of(SingleProductInputDTO.class)
                .generate(field(SingleProductInputDTO::getId), gen -> gen.text().uuid())
                .set(field(SingleProductInputDTO::getName), "Lanche")
                .set(field(SingleProductInputDTO::getPrice), BigDecimal.valueOf(17.00))
                .set(field(SingleProductInputDTO::getFullPrice), BigDecimal.valueOf(17.00))
                .set(field(SingleProductInputDTO::getDiscount), BigDecimal.ZERO)
                .set(field(SingleProductInputDTO::getCategory), ProductCategory.SANDWICH)
                .toModel();
    }

    public static Model<ComboProductInputDTO> comboInputDTO() {
        return Instancio.of(ComboProductInputDTO.class)
                .generate(field(ComboProductInputDTO::getId), gen -> gen.text().uuid())
                .set(field(ComboProductInputDTO::getName), "Combo")
                .set(field(ComboProductInputDTO::getPrice), BigDecimal.valueOf(20.00))
                .set(field(ComboProductInputDTO::getFullPrice), BigDecimal.valueOf(25.00))
                .set(field(ComboProductInputDTO::getDiscount), BigDecimal.valueOf(5.00))
                .set(field(ComboProductInputDTO::getBeverage), Instancio.create(beverageInputDTO()))
                .set(field(ComboProductInputDTO::getSideDish), Instancio.create(sideDishInputDTO()))
                .set(field(ComboProductInputDTO::getSandwich), Instancio.create(sandwichInputDTO()))
                .set(field(SingleProductInputDTO::getCategory), ProductCategory.COMBO)
                .toModel();
    }

}
