package br.com.fiap.tech.challenge.purchase.adapter.util;

import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.purchase.adapter.dto.ComboInputDTO;
import br.com.fiap.tech.challenge.purchase.adapter.dto.ProductInputDTO;
import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseInputDTO;
import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseItemInputDTO;
import br.com.fiap.tech.challenge.purchase.adapter.mapping.CreatePurchaseDTOMapping;
import br.com.fiap.tech.challenge.purchase.application.dto.CreatePurchaseDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.ProductDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.PurchaseItemDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static br.com.fiap.tech.challenge.purchase.enterprise.error.ApplicationError.PRODUCT_PRICES_IS_INVALID;

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

    private List<PurchaseItemDTO> buildItems(List<PurchaseItemInputDTO> items) {
        return items.stream()
                .flatMap(this::dismember)
                .toList();
    }

    private Stream<PurchaseItemDTO> dismember(PurchaseItemInputDTO item) {
        if (!(item.getProduct() instanceof ComboInputDTO combo)) {
            return Stream.of(buildItem(item));
        }

        var price = combo.getFullPrice();
        var targetPrice = combo.getPrice();

        var products = new ArrayList<ProductInputDTO>();
        products.add(combo.getBeverage());
        products.add(combo.getSideDish());
        products.add(combo.getSandwich());

        products.sort(Comparator.comparing(ProductInputDTO::getPrice));

        var result = new ArrayList<PurchaseItemDTO>();

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
            throw new ApplicationException(PRODUCT_PRICES_IS_INVALID, combo.getId());
        }

        return result.stream();
    }

    private PurchaseItemDTO buildItem(PurchaseItemInputDTO dto) {
        return new PurchaseItemDTO()
                .setDiscount(dto.getDiscount())
                .setPrice(dto.getPrice())
                .setQuantity(dto.getQuantity())
                .setProduct(buildProduct(dto.getProduct()));
    }

    private PurchaseItemDTO buildItem(PurchaseItemInputDTO dto, ProductInputDTO product, BigDecimal discount) {
        return new PurchaseItemDTO()
                .setDiscount(discount)
                .setPrice(product.getPrice())
                .setQuantity(dto.getQuantity())
                .setProduct(buildProduct(product));
    }

    private ProductDTO buildProduct(ProductInputDTO dto) {
        return new ProductDTO()
                .setPrice(dto.getPrice())
                .setDescription(dto.getDescription())
                .setId(dto.getId())
                .setName(dto.getName());
    }
}
