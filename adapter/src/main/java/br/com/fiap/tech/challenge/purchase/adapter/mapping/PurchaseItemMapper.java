package br.com.fiap.tech.challenge.purchase.adapter.mapping;

import br.com.fiap.tech.challenge.purchase.application.dto.ProductDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.PurchaseItemDTO;
import br.com.fiap.tech.challenge.purchase.adapter.mapping.util.ProductMappers;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Product;
import br.com.fiap.tech.challenge.purchase.enterprise.valueobject.PurchaseItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static br.com.fiap.tech.challenge.util.Mappings.discountToBigDecimalConverter;
import static br.com.fiap.tech.challenge.util.Mappings.priceToBigDecimalConverter;
import static br.com.fiap.tech.challenge.util.Mappings.quantityToIntegerConverter;

@Mapper(uses = { CommonMapper.class })
public interface PurchaseItemMapper {

    PurchaseItemMapper INSTANCE = Mappers.getMapper(PurchaseItemMapper.class);

    @Mapping(target = "product", source = "item", qualifiedByName = "mapToProductDTO")
    @Mapping(target = "price", source = "item", qualifiedByName = "mapPrice")
    @Mapping(target = "fullPrice", source = "item", qualifiedByName = "mapFullPrice")
    @Mapping(target = "discount", source = "item", qualifiedByName = "mapDiscount")
    @Mapping(target = "quantity", source = "item", qualifiedByName = "mapQuantity")
    PurchaseItemDTO toDTO(PurchaseItem item);

    @Mapping(target = "product", source = "product", qualifiedByName = "mapToProductDomain")
    @Mapping(target = "price", source = "price", qualifiedByName = "getPrice")
    @Mapping(target = "fullPrice", source = "fullPrice", qualifiedByName = "getPrice")
    @Mapping(target = "discount", source = "discount", qualifiedByName = "getDiscount")
    @Mapping(target = "quantity", source = "quantity", qualifiedByName = "getQuantityVO")
    PurchaseItem toDomain(PurchaseItemDTO dto);

    @Named("mapToProductDomain")
    static Product mapToProductDomain(ProductDTO dto) {
        return ProductMappers.toProductDomain(dto);
    }

    @Named("mapToProductDTO")
    static ProductDTO mapToProductDTO(PurchaseItem item){
        return ProductMappers.toProductDTO(item.product());
    }

    @Named("mapFullPrice")
    static BigDecimal mapFullPrice(PurchaseItem item) {
        return priceToBigDecimalConverter(item.fullPrice());
    }

    @Named("mapPrice")
    static BigDecimal mapPrice(PurchaseItem item) {
        return priceToBigDecimalConverter(item.price());
    }

    @Named("mapQuantity")
    static Integer mapQuantity(PurchaseItem item) {
        return quantityToIntegerConverter(item.quantity());
    }

    @Named("mapDiscount")
    static BigDecimal mapDiscount(PurchaseItem item) {
        return discountToBigDecimalConverter(item.discount());
    }



}
