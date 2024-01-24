package br.com.fiap.tech.challenge.purchase.application.fixture;

import br.com.fiap.tech.challenge.purchase.application.dto.PurchaseItemDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import java.math.BigDecimal;

import static br.com.fiap.tech.challenge.purchase.application.fixture.ProductDTOFixture.beverageDTOModel;
import static br.com.fiap.tech.challenge.purchase.application.fixture.ProductDTOFixture.sandwichDTOModel;
import static br.com.fiap.tech.challenge.purchase.application.fixture.ProductDTOFixture.sideDishDTOModel;
import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PurchaseItemDTOFixture {

    public static Model<PurchaseItemDTO> singleBeverageItemDTO() {
        return Instancio.of(PurchaseItemDTO.class)
                .set(field(PurchaseItemDTO::getProduct), Instancio.create(beverageDTOModel()))
                .set(field(PurchaseItemDTO::getPrice), BigDecimal.valueOf(5.00))
                .set(field(PurchaseItemDTO::getDiscount), BigDecimal.ZERO)
                .set(field(PurchaseItemDTO::getQuantity), 1)
                .toModel();
    }

    public static Model<PurchaseItemDTO> singleSideDishItemDTO() {
        return Instancio.of(PurchaseItemDTO.class)
                .set(field(PurchaseItemDTO::getProduct), Instancio.create(sideDishDTOModel()))
                .set(field(PurchaseItemDTO::getPrice), BigDecimal.valueOf(3.00))
                .set(field(PurchaseItemDTO::getDiscount), BigDecimal.ZERO)
                .set(field(PurchaseItemDTO::getQuantity), 1)
                .toModel();
    }

    public static Model<PurchaseItemDTO> singleSandwichItemDTO() {
        return Instancio.of(PurchaseItemDTO.class)
                .set(field(PurchaseItemDTO::getProduct), Instancio.create(sandwichDTOModel()))
                .set(field(PurchaseItemDTO::getPrice), BigDecimal.valueOf(17.00))
                .set(field(PurchaseItemDTO::getDiscount), BigDecimal.ZERO)
                .set(field(PurchaseItemDTO::getQuantity), 1)
                .toModel();
    }
}
