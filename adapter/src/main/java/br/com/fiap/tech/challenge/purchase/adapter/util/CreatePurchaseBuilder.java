package br.com.fiap.tech.challenge.purchase.adapter.util;

import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.purchase.adapter.dto.ComboProductInputDTO;
import br.com.fiap.tech.challenge.purchase.adapter.dto.SingleProductInputDTO;
import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseInputDTO;
import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseItemInputDTO;
import br.com.fiap.tech.challenge.purchase.adapter.mapping.CreatePurchaseDTOMapping;
import br.com.fiap.tech.challenge.purchase.application.dto.CreatePurchaseDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.FullProductDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.FullPurchaseItemDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static br.com.fiap.tech.challenge.purchase.enterprise.error.ApplicationError.PRODUCT_PRICE_IS_INVALID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreatePurchaseBuilder {

    public static CreatePurchaseBuilder get() {
        return new CreatePurchaseBuilder();
    }

    public CreatePurchaseDTO build(PurchaseInputDTO dto) {
        var purchaseDTO = CreatePurchaseDTOMapping.INSTANCE.toCreatePurchaseDTO(dto);
        purchaseDTO.setItems(buildItems(dto.getItems()));

        return purchaseDTO;
    }

    private List<FullPurchaseItemDTO> buildItems(List<PurchaseItemInputDTO> items) {
        return items.stream()
                .flatMap(this::dismember)
                .toList();
    }

    private Stream<FullPurchaseItemDTO> dismember(PurchaseItemInputDTO item) {
        if (!(item.getProduct() instanceof ComboProductInputDTO combo)) {
            return Stream.of(buildItem(item));
        }

        var price = combo.getFullPrice();
        var targetPrice = combo.getPrice();

        var products = new ArrayList<SingleProductInputDTO>();
        products.add(combo.getBeverage());
        products.add(combo.getSideDish());
        products.add(combo.getSandwich());

        products.sort(Comparator.comparing(SingleProductInputDTO::getPrice));

        var result = new ArrayList<FullPurchaseItemDTO>();

        for (var product : products) {
            if (price.compareTo(targetPrice) == 0) {
                result.add(buildItem(item, product, product.getDiscount()));
                continue;
            }

            var diff = price.subtract(targetPrice);

            if (product.getPrice().compareTo(diff) > 0) {
                result.add(buildItem(item, product, diff));
                price = price.subtract(diff);
            } else {
                var acceptableDiscount = product.getPrice().subtract(new BigDecimal("0.01"));
                result.add(buildItem(item, product, acceptableDiscount));
                price = price.subtract(acceptableDiscount);
            }
        }

        if (price.compareTo(targetPrice) != 0) {
            throw new ApplicationException(PRODUCT_PRICE_IS_INVALID, combo.getId());
        }

        return result.stream();
    }

    private FullPurchaseItemDTO buildItem(PurchaseItemInputDTO dto) {
        return new FullPurchaseItemDTO()
                .setDiscount(dto.getDiscount())
                .setPrice(dto.getProduct().getPrice())
                .setQuantity(dto.getQuantity())
                .setProduct(buildProduct(dto.getProduct()));
    }

    private FullPurchaseItemDTO buildItem(PurchaseItemInputDTO dto, SingleProductInputDTO product, BigDecimal discount) {
        return new FullPurchaseItemDTO()
                .setDiscount(discount)
                .setPrice(product.getPrice())
                .setQuantity(dto.getQuantity())
                .setProduct(buildProduct(product));
    }

    private FullProductDTO buildProduct(SingleProductInputDTO dto) {
        return new FullProductDTO()
                .setPrice(dto.getPrice())
                .setDescription(dto.getDescription())
                .setId(dto.getId())
                .setName(dto.getName())
                .setCategory(dto.getCategory());
    }
}
