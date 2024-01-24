package br.com.fiap.tech.challenge.purchase.rest.mapping;

import br.com.fiap.tech.challenge.purchase.application.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.purchase.rest.resource.response.PurchaseResponse;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING,
        uses = { PurchaseItemResponseMapper.class, CustomerResponseMapper.class, PaymentResponseMapper.class }
)
public interface PurchaseResponseMapper {

    PurchaseResponse toResponse(PurchaseDTO dto);
}
