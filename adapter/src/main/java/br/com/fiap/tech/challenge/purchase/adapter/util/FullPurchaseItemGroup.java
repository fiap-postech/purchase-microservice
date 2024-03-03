package br.com.fiap.tech.challenge.purchase.adapter.util;

import br.com.fiap.tech.challenge.purchase.adapter.dto.GroupItemTuple;
import br.com.fiap.tech.challenge.purchase.application.dto.FullPurchaseItemDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.PurchaseDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FullPurchaseItemGroup {

    public static PurchaseDTO groupEqualItems(PurchaseDTO dto) {
        var items = dto.getItems()
                .stream()
                .collect(Collectors.groupingBy(i -> new GroupItemTuple(i.getProduct().getId(), i.getPrice(), i.getDiscount())))
                .values()
                .stream()
                .map(FullPurchaseItemGroup::groupedItem)
                .toList();

        dto.setItems(items);

        return dto;
    }

    private static FullPurchaseItemDTO groupedItem(List<FullPurchaseItemDTO> items) {
        if (items.size() == 1) {
            return items.get(0);
        }

        var item = items.get(0);

        var quantity = items.stream()
                .map(FullPurchaseItemDTO::getQuantity)
                .reduce(0, Integer::sum);

        item.setQuantity(quantity);

        return item;
    }



}
