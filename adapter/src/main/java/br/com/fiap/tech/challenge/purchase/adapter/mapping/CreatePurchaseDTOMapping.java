package br.com.fiap.tech.challenge.purchase.adapter.mapping;

import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseInputDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.CreatePurchaseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreatePurchaseDTOMapping {

    CreatePurchaseDTOMapping INSTANCE = Mappers.getMapper(CreatePurchaseDTOMapping.class);

    @Mapping(target = "items", ignore = true)
    @Mapping(target = "externalId", source = "id")
    CreatePurchaseDTO toCreatePurchaseDTO(PurchaseInputDTO dto);

}
