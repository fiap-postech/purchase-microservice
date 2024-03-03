package br.com.fiap.tech.challenge.purchase.launcher.fixture.input;

import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseItemInputDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import java.math.BigDecimal;

import static br.com.fiap.tech.challenge.purchase.launcher.fixture.input.ProductInputDTOFixture.beverageInputDTO;
import static br.com.fiap.tech.challenge.purchase.launcher.fixture.input.ProductInputDTOFixture.comboInputDTO;
import static br.com.fiap.tech.challenge.purchase.launcher.fixture.input.ProductInputDTOFixture.sandwichInputDTO;
import static br.com.fiap.tech.challenge.purchase.launcher.fixture.input.ProductInputDTOFixture.sideDishInputDTO;
import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PurchaseItemInputDTOFixture {

    public static Model<PurchaseItemInputDTO> singleBeverageInputItemDTO() {
        return Instancio.of(PurchaseItemInputDTO.class)
                .set(field(PurchaseItemInputDTO::getProduct), Instancio.create(beverageInputDTO()))
                .set(field(PurchaseItemInputDTO::getTotal), BigDecimal.valueOf(5.00))
                .set(field(PurchaseItemInputDTO::getSubTotal), BigDecimal.valueOf(5.00))
                .set(field(PurchaseItemInputDTO::getDiscount), BigDecimal.ZERO)
                .set(field(PurchaseItemInputDTO::getQuantity), 1)
                .toModel();
    }

    public static Model<PurchaseItemInputDTO> singleSideDishInputItemDTO() {
        return Instancio.of(PurchaseItemInputDTO.class)
                .set(field(PurchaseItemInputDTO::getProduct), Instancio.create(sideDishInputDTO()))
                .set(field(PurchaseItemInputDTO::getTotal), BigDecimal.valueOf(3.00))
                .set(field(PurchaseItemInputDTO::getSubTotal), BigDecimal.valueOf(3.00))
                .set(field(PurchaseItemInputDTO::getDiscount), BigDecimal.ZERO)
                .set(field(PurchaseItemInputDTO::getQuantity), 1)
                .toModel();
    }

    public static Model<PurchaseItemInputDTO> singleSandwichInputItemDTO() {
        return Instancio.of(PurchaseItemInputDTO.class)
                .set(field(PurchaseItemInputDTO::getProduct), Instancio.create(sandwichInputDTO()))
                .set(field(PurchaseItemInputDTO::getTotal), BigDecimal.valueOf(17.00))
                .set(field(PurchaseItemInputDTO::getSubTotal), BigDecimal.valueOf(17.00))
                .set(field(PurchaseItemInputDTO::getDiscount), BigDecimal.ZERO)
                .set(field(PurchaseItemInputDTO::getQuantity), 1)
                .toModel();
    }

    public static Model<PurchaseItemInputDTO> singleComboInputItemDTO() {
        return Instancio.of(PurchaseItemInputDTO.class)
                .set(field(PurchaseItemInputDTO::getProduct), Instancio.create(comboInputDTO()))
                .set(field(PurchaseItemInputDTO::getTotal), BigDecimal.valueOf(20.00))
                .set(field(PurchaseItemInputDTO::getSubTotal), BigDecimal.valueOf(25.00))
                .set(field(PurchaseItemInputDTO::getDiscount), BigDecimal.valueOf(5.00))
                .set(field(PurchaseItemInputDTO::getQuantity), 1)
                .toModel();
    }
}
