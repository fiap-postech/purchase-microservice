package br.com.fiap.tech.challenge.purchase.application.mapper;

import br.com.fiap.tech.challenge.purchase.application.dto.CreatePurchaseDTO;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CommonMapper.class})
public interface CreatePurchaseMapper {

    CreatePurchaseMapper INSTANCE = Mappers.getMapper(CreatePurchaseMapper.class);

    Purchase toDomain(CreatePurchaseDTO dto);

}
