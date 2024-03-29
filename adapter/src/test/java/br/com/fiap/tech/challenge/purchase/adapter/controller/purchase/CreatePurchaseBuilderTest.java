package br.com.fiap.tech.challenge.purchase.adapter.controller.purchase;

import br.com.fiap.tech.challenge.purchase.adapter.dto.ComboProductInputDTO;
import br.com.fiap.tech.challenge.purchase.adapter.dto.SingleProductInputDTO;
import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseInputDTO;
import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseItemInputDTO;
import br.com.fiap.tech.challenge.purchase.adapter.util.CreatePurchaseBuilder;
import br.com.fiap.tech.challenge.purchase.application.dto.CreatePurchaseDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.FullProductDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static br.com.fiap.tech.challenge.purchase.adapter.fixture.Fixture.create;
import static br.com.fiap.tech.challenge.purchase.adapter.fixture.PurchaseInputDTOFixture.comboAndSandwichAndBeveragePurchaseInputDTO;
import static br.com.fiap.tech.challenge.purchase.adapter.fixture.PurchaseInputDTOFixture.sandwichAndBeveragePurchaseInputDTO;
import static br.com.fiap.tech.challenge.purchase.adapter.fixture.PurchaseInputDTOFixture.singleBeveragePurchaseInputDTO;
import static br.com.fiap.tech.challenge.purchase.adapter.fixture.PurchaseInputDTOFixture.singleComboPurchaseInputDTO;
import static br.com.fiap.tech.challenge.purchase.adapter.fixture.PurchaseInputDTOFixture.singleSandwichPurchaseInputDTO;
import static br.com.fiap.tech.challenge.purchase.adapter.fixture.PurchaseInputDTOFixture.singleSideDishPurchaseInputDTO;
import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;
import static org.assertj.core.api.Assertions.assertThat;

class CreatePurchaseBuilderTest {

    @Test
    void testBuildPurchaseWithSingleBeverage() {
        var input = create(singleBeveragePurchaseInputDTO());
        var response = CreatePurchaseBuilder.get().build(input);

        validatePurchase(input, response);

        var inputItem = input.getItems().get(0);
        var responseItem = response.getItems().get(0);

        assertThat(responseItem.getDiscount()).isEqualTo(inputItem.getDiscount());
        assertThat(responseItem.getQuantity()).isEqualTo(inputItem.getQuantity());
        assertThat(responseItem.getPrice().multiply(BigDecimal.valueOf(responseItem.getQuantity()))).isEqualTo(inputItem.getSubTotal());

        validateProduct(inputItem.getProduct(), responseItem.getProduct());
    }

    @Test
    void testBuildPurchaseWithSingleSideDish() {
        var input = create(singleSideDishPurchaseInputDTO());
        var response = CreatePurchaseBuilder.get().build(input);

        validatePurchase(input, response);

        assertThat(response.getItems()).hasSize(1);

        var inputItem = input.getItems().get(0);
        var responseItem = response.getItems().get(0);

        assertThat(responseItem.getDiscount()).isEqualTo(inputItem.getDiscount());
        assertThat(responseItem.getQuantity()).isEqualTo(inputItem.getQuantity());
        assertThat(responseItem.getPrice()).isEqualTo(inputItem.getSubTotal());

        validateProduct(inputItem.getProduct(), responseItem.getProduct());
    }

    @Test
    void testBuildPurchaseWithSingleSandwich() {
        var input = create(singleSandwichPurchaseInputDTO());
        var response = CreatePurchaseBuilder.get().build(input);

        validatePurchase(input, response);

        assertThat(response.getItems()).hasSize(1);

        var inputItem = input.getItems().get(0);
        var responseItem = response.getItems().get(0);

        assertThat(responseItem.getDiscount()).isEqualTo(inputItem.getDiscount());
        assertThat(responseItem.getQuantity()).isEqualTo(inputItem.getQuantity());
        assertThat(responseItem.getPrice()).isEqualTo(inputItem.getSubTotal());

        validateProduct(inputItem.getProduct(), responseItem.getProduct());
    }

    @Test
    void testBuildPurchaseWithSingleCombo() {
        var input = create(singleComboPurchaseInputDTO());
        var response = CreatePurchaseBuilder.get().build(input);

        validatePurchaseWithManyProducts(input, response, 3);
    }

    @Test
    void testBuildPurchaseWithSandwichAndBeverage() {
        var input = create(sandwichAndBeveragePurchaseInputDTO());
        var response = CreatePurchaseBuilder.get().build(input);

        validatePurchaseWithManyProducts(input, response, 3);
    }

    @Test
    void testBuildPurchaseWithComboAndSandwichAndBeverage() {
        var input = create(comboAndSandwichAndBeveragePurchaseInputDTO());
        var response = CreatePurchaseBuilder.get().build(input);

        validatePurchaseWithManyProducts(input, response, 6);
    }

    private void validatePurchase(PurchaseInputDTO input, CreatePurchaseDTO response) {
        assertThat(response.getDate()).isEqualTo(input.getDate());
        assertThat(response.getStatus()).isEqualTo(input.getStatus());

        assertThat(response.getCustomer().getName()).isEqualTo(input.getCustomer().getName());
        assertThat(response.getCustomer().getId()).isEqualTo(input.getCustomer().getId());
        assertThat(response.getCustomer().getEmail()).isEqualTo(input.getCustomer().getEmail());
        assertThat(response.getCustomer().getDocument()).isEqualTo(input.getCustomer().getDocument());
    }

    private void validatePurchaseWithManyProducts(PurchaseInputDTO input, CreatePurchaseDTO createPurchase, int expectedItems) {
        validatePurchase(input, createPurchase);

        var discount = BigDecimal.ZERO;
        var quantity = 0;
        var price = BigDecimal.ZERO;

        for (var item : createPurchase.getItems()) {
            quantity += item.getQuantity();

            var quantityValue = BigDecimal.valueOf(item.getQuantity());

            discount = discount.add(item.getDiscount().multiply(quantityValue));
            price = price.add(item.getPrice().multiply(quantityValue));

            var op = getProduct(input, item.getProduct().getId());

            assertThat(op).isNotNull().isPresent();

            op.ifPresent(p -> validateProduct(p, item.getProduct()));
        }

        assertThat(quantity).isEqualTo(expectedItems);
        assertThat(makeMoney(discount)).isEqualTo(makeMoney(getPurchaseDiscount(input)));
        assertThat(makeMoney(price)).isEqualTo(makeMoney(getPurchaseTotalWithoutDiscount(input)));
    }

    private void validateProduct(SingleProductInputDTO input, FullProductDTO dto) {
        assertThat(dto.getDescription()).isEqualTo(input.getDescription());
        assertThat(dto.getId()).isEqualTo(input.getId());
        assertThat(dto.getPrice()).isEqualTo(input.getFullPrice());
        assertThat(dto.getName()).isEqualTo(input.getName());
        assertThat(dto.getCategory()).isEqualTo(input.getCategory());
    }

    private Optional<SingleProductInputDTO> getProduct(PurchaseInputDTO dto, String id) {
        for (var item : dto.getItems()) {
            if (item.getProduct() instanceof ComboProductInputDTO combo) {
                if (combo.getSandwich().getId().equals(id)) {
                    return Optional.of(combo.getSandwich());
                }

                if (combo.getBeverage().getId().equals(id)) {
                    return Optional.of(combo.getBeverage());
                }

                if (combo.getSideDish().getId().equals(id)) {
                    return Optional.of(combo.getSideDish());
                }
            }

            if (item.getProduct().getId().equals(id)) {
                return Optional.of(item.getProduct());
            }
        }

        return Optional.empty();
    }

    private BigDecimal getPurchaseDiscount(PurchaseInputDTO dto) {
        return dto.getItems()
                .stream()
                .map(PurchaseItemInputDTO::getDiscount)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private BigDecimal getPurchaseTotalWithoutDiscount(PurchaseInputDTO dto) {
        return dto.getItems()
                .stream()
                .map(PurchaseItemInputDTO::getSubTotal)
                .reduce(BigDecimal::add)
                .orElseThrow();
    }
}
