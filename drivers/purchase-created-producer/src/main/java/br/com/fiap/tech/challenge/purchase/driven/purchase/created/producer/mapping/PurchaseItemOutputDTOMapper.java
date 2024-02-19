package br.com.fiap.tech.challenge.purchase.driven.purchase.created.producer.mapping;

import br.com.fiap.tech.challenge.purchase.application.dto.FullPurchaseItemDTO;
import br.com.fiap.tech.challenge.purchase.driven.purchase.created.producer.dto.PurchaseItemOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface PurchaseItemOutputDTOMapper {

    @Mapping(target = "title", source = "product.name")
    @Mapping(target = "description", source = "product.description")
    @Mapping(target = "categoryId", expression = "java(dto.getProduct().getCategory().name())")
    @Mapping(target = "unitPrice", source = "dto", qualifiedByName = "getUnitPrice")
    PurchaseItemOutputDTO toOutputDTO(FullPurchaseItemDTO dto);

    @Named("getUnitPrice")
    static BigDecimal getUnitPrice(FullPurchaseItemDTO dto) {
        return dto.getPrice().subtract(dto.getDiscount());
    }
}
