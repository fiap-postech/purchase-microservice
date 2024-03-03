package br.com.fiap.tech.challenge.purchase.launcher.fixture.input;

import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseInputDTO;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import java.util.List;

import static br.com.fiap.tech.challenge.purchase.launcher.fixture.input.CustomerInputDTOFixture.customerInputDTO;
import static br.com.fiap.tech.challenge.purchase.launcher.fixture.input.PaymentInputDTOFixture.paymentInputDTO;
import static br.com.fiap.tech.challenge.purchase.launcher.fixture.input.PurchaseItemInputDTOFixture.singleBeverageInputItemDTO;
import static br.com.fiap.tech.challenge.purchase.launcher.fixture.input.PurchaseItemInputDTOFixture.singleComboInputItemDTO;
import static br.com.fiap.tech.challenge.purchase.launcher.fixture.input.PurchaseItemInputDTOFixture.singleSandwichInputItemDTO;
import static br.com.fiap.tech.challenge.purchase.launcher.fixture.input.PurchaseItemInputDTOFixture.singleSideDishInputItemDTO;
import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PurchaseInputDTOFixture {

    public static Model<PurchaseInputDTO> singleBeveragePurchaseInputDTO() {
        return Instancio.of(PurchaseInputDTO.class)
                .generate(field(PurchaseInputDTO::getId), gen -> gen.text().uuid())
                .generate(field(PurchaseInputDTO::getDate), gen -> gen.temporal().localDate())
                .set(field(PurchaseInputDTO::getItems), List.of(Instancio.create(singleBeverageInputItemDTO())))
                .set(field(PurchaseInputDTO::getCustomer), Instancio.create(customerInputDTO()))
                .set(field(PurchaseInputDTO::getStatus), PurchaseStatus.CREATED)
                .toModel();
    }

    public static Model<PurchaseInputDTO> singleSideDishPurchaseInputDTO() {
        return Instancio.of(singleBeveragePurchaseInputDTO())
                .set(field(PurchaseInputDTO::getItems), List.of(Instancio.create(singleSideDishInputItemDTO())))
                .toModel();
    }

    public static Model<PurchaseInputDTO> singleSandwichPurchaseInputDTO() {
        return Instancio.of(singleBeveragePurchaseInputDTO())
                .set(field(PurchaseInputDTO::getItems), List.of(Instancio.create(singleSandwichInputItemDTO())))
                .toModel();
    }

    public static Model<PurchaseInputDTO> singleComboPurchaseInputDTO() {
        return Instancio.of(singleBeveragePurchaseInputDTO())
                .set(field(PurchaseInputDTO::getItems), List.of(Instancio.create(singleComboInputItemDTO())))
                .toModel();
    }

    public static Model<PurchaseInputDTO> sandwichAndBeveragePurchaseInputDTO() {
        return Instancio.of(singleBeveragePurchaseInputDTO())
                .set(field(PurchaseInputDTO::getItems), List.of(
                        Instancio.create(singleSandwichInputItemDTO()),
                        Instancio.create(singleBeverageInputItemDTO())
                ))
                .toModel();
    }

    public static Model<PurchaseInputDTO> comboAndSandwichAndBeveragePurchaseInputDTO() {
        return Instancio.of(singleBeveragePurchaseInputDTO())
                .set(field(PurchaseInputDTO::getItems), List.of(
                        Instancio.create(singleSandwichInputItemDTO()),
                        Instancio.create(singleBeverageInputItemDTO()),
                        Instancio.create(singleComboInputItemDTO())
                ))
                .toModel();
    }



}
