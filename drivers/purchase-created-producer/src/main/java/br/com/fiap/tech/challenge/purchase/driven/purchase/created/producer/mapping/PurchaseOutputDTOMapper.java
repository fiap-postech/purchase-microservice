package br.com.fiap.tech.challenge.purchase.driven.purchase.created.producer.mapping;

import br.com.fiap.tech.challenge.purchase.application.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.purchase.driven.purchase.created.producer.dto.PurchaseOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = { PurchaseItemOutputDTOMapper.class })
public interface PurchaseOutputDTOMapper {

    @Mapping(target = "total", source = "dto", qualifiedByName = "getTotal")
    PurchaseOutputDTO toOutputDTO(PurchaseDTO dto);

    @Named("getTotal")
    static BigDecimal getTotal(PurchaseDTO dto) {
        return dto.getItems().stream()
                .map(i -> i.getPrice().subtract(i.getDiscount()))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
