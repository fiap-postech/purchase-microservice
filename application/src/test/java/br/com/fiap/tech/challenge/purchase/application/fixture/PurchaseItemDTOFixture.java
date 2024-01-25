package br.com.fiap.tech.challenge.purchase.application.fixture;

import br.com.fiap.tech.challenge.purchase.application.dto.FullPurchaseItemDTO;
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

    public static Model<FullPurchaseItemDTO> singleBeverageItemDTO() {
        return Instancio.of(FullPurchaseItemDTO.class)
                .set(field(FullPurchaseItemDTO::getProduct), Instancio.create(beverageDTOModel()))
                .set(field(FullPurchaseItemDTO::getPrice), BigDecimal.valueOf(5.00))
                .set(field(FullPurchaseItemDTO::getDiscount), BigDecimal.ZERO)
                .set(field(FullPurchaseItemDTO::getQuantity), 1)
                .toModel();
    }

    public static Model<FullPurchaseItemDTO> singleSideDishItemDTO() {
        return Instancio.of(FullPurchaseItemDTO.class)
                .set(field(FullPurchaseItemDTO::getProduct), Instancio.create(sideDishDTOModel()))
                .set(field(FullPurchaseItemDTO::getPrice), BigDecimal.valueOf(3.00))
                .set(field(FullPurchaseItemDTO::getDiscount), BigDecimal.ZERO)
                .set(field(FullPurchaseItemDTO::getQuantity), 1)
                .toModel();
    }

    public static Model<FullPurchaseItemDTO> singleSandwichItemDTO() {
        return Instancio.of(FullPurchaseItemDTO.class)
                .set(field(FullPurchaseItemDTO::getProduct), Instancio.create(sandwichDTOModel()))
                .set(field(FullPurchaseItemDTO::getPrice), BigDecimal.valueOf(17.00))
                .set(field(FullPurchaseItemDTO::getDiscount), BigDecimal.ZERO)
                .set(field(FullPurchaseItemDTO::getQuantity), 1)
                .toModel();
    }
}
