package br.com.fiap.tech.challenge.purchase.application.mapper;

import br.com.fiap.tech.challenge.purchase.application.dto.FullProductDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.FullPurchaseItemDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.SimpleProductDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.SimplePurchaseItemDTO;
import br.com.fiap.tech.challenge.purchase.enterprise.valueobject.PurchaseItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static br.com.fiap.tech.challenge.util.Mappings.discountToBigDecimalConverter;
import static br.com.fiap.tech.challenge.util.Mappings.priceToBigDecimalConverter;

@Mapper(uses = { CommonMapper.class, ProductMapper.class })
public interface PurchaseItemMapper {

    PurchaseItemMapper INSTANCE = Mappers.getMapper(PurchaseItemMapper.class);

    @Mapping(target = "product", source = "item", qualifiedByName = "getProductDTO")
    @Mapping(target = "discount", source = "item", qualifiedByName = "getDiscountAsBigDecimal")
    @Mapping(target = "price", source = "item", qualifiedByName = "getPriceAsBigDecimal")
    @Mapping(target = "quantity", source = "item", qualifiedByName = "getQuantityInt")
    FullPurchaseItemDTO toDTO(PurchaseItem item);

    @Mapping(target = "product", source = "item", qualifiedByName = "getSimpleProductDTO")
    @Mapping(target = "quantity", source = "item", qualifiedByName = "getQuantityInt")
    SimplePurchaseItemDTO toSimpleDTO(PurchaseItem item);

    @Mapping(target = "product", source = "product")
    @Mapping(target = "price", source = "price", qualifiedByName = "getPrice")
    @Mapping(target = "discount", source = "discount", qualifiedByName = "getDiscount")
    @Mapping(target = "quantity", source = "quantity", qualifiedByName = "getQuantityVO")
    PurchaseItem toDomain(FullPurchaseItemDTO dto);

    @Named("getProductDTO")
    static FullProductDTO getProductDTO(PurchaseItem item) {
        return ProductMapper.INSTANCE.toDTO(item.product());
    }

    @Named("getSimpleProductDTO")
    static SimpleProductDTO getSimpleProductDTO(PurchaseItem item) {
        return ProductMapper.INSTANCE.toSimpleDTO(item.product());
    }

    @Named("getQuantityInt")
    static Integer quantityToInt(PurchaseItem item) {
        return item.quantity().value();
    }

    @Named("getDiscountAsBigDecimal")
    static BigDecimal discoutToBigDecimal(PurchaseItem item) {
        return discountToBigDecimalConverter(item.discount());
    }

    @Named("getPriceAsBigDecimal")
    static BigDecimal priceToBigDecimal(PurchaseItem item) {
        return priceToBigDecimalConverter(item.price());
    }
}
