package br.com.fiap.tech.challenge.purchase.launcher.fixture;

import br.com.fiap.tech.challenge.purchase.application.dto.CreatePurchaseDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.FullPurchaseItemDTO;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import java.math.BigDecimal;
import java.util.List;

import static br.com.fiap.tech.challenge.purchase.launcher.fixture.CustomerDTOFixture.customerDTOModel;
import static br.com.fiap.tech.challenge.purchase.launcher.fixture.PaymentDTOFixture.paymentDTOModel;
import static br.com.fiap.tech.challenge.purchase.launcher.fixture.PurchaseItemDTOFixture.singleBeverageItemDTO;
import static br.com.fiap.tech.challenge.purchase.launcher.fixture.PurchaseItemDTOFixture.singleSandwichItemDTO;
import static br.com.fiap.tech.challenge.purchase.launcher.fixture.PurchaseItemDTOFixture.singleSideDishItemDTO;
import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreatePurchaseDTOFixture {

    public static Model<CreatePurchaseDTO> singleBeverageCreatePurchaseDTO() {
        return Instancio.of(CreatePurchaseDTO.class)
                .generate(field(CreatePurchaseDTO::getDate), gen -> gen.temporal().localDate())
                .set(field(CreatePurchaseDTO::getItems), List.of(Instancio.create(singleBeverageItemDTO())))
                .set(field(CreatePurchaseDTO::getCustomer), Instancio.create(customerDTOModel()))
                .set(field(CreatePurchaseDTO::getStatus), PurchaseStatus.PAID)
                .set(field(CreatePurchaseDTO::getPayment), Instancio.of(paymentDTOModel()).create())
                .toModel();
    }

    public static Model<CreatePurchaseDTO> singleSideDishCreatePurchaseDTO() {
        return Instancio.of(singleBeverageCreatePurchaseDTO())
                .set(field(CreatePurchaseDTO::getItems), List.of(Instancio.create(singleSideDishItemDTO())))
                .toModel();
    }

    public static Model<CreatePurchaseDTO> singleSandwichCreatePurchaseDTO() {
        return Instancio.of(singleBeverageCreatePurchaseDTO())
                .set(field(CreatePurchaseDTO::getItems), List.of(Instancio.create(singleSandwichItemDTO())))
                .toModel();
    }

    public static Model<CreatePurchaseDTO> sandwichAndBeverageCreatePurchaseDTO() {
        return Instancio.of(singleBeverageCreatePurchaseDTO())
                .set(field(CreatePurchaseDTO::getItems), List.of(
                        Instancio.create(singleSandwichItemDTO()),
                        Instancio.create(singleBeverageItemDTO())
                ))
                .toModel();
    }

    public static Model<CreatePurchaseDTO> comboAndSandwichAndBeverageCreatePurchaseDTO() {
        return Instancio.of(singleBeverageCreatePurchaseDTO())
                .set(field(CreatePurchaseDTO::getItems), List.of(
                        Instancio.create(singleSandwichItemDTO()),
                        Instancio.create(singleBeverageItemDTO()),
                        Instancio.of(singleBeverageItemDTO())
                                .set(field(FullPurchaseItemDTO::getDiscount), BigDecimal.valueOf(2.01))
                                .create(),
                        Instancio.of(singleSideDishItemDTO())
                                .set(field(FullPurchaseItemDTO::getDiscount), BigDecimal.valueOf(2.99))
                                .create(),
                        Instancio.create(singleSandwichItemDTO())
                ))
                .toModel();
    }

}
