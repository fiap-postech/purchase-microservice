package br.com.fiap.tech.challenge.purchase.rest.mapping;

import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.purchase.rest.resource.response.PurchseResponse;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING,
        uses = { PurchaseItemResponseMapper.class, CustomerResponseMapper.class, PaymentResponseMapper.class }
)
public interface PurchaseResponseMapper {

    PurchseResponse toResponse(PurchaseDTO dto);
}
