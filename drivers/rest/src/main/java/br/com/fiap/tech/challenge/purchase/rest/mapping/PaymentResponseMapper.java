package br.com.fiap.tech.challenge.purchase.rest.mapping;

import br.com.fiap.tech.challenge.purchase.application.dto.PaymentDTO;
import br.com.fiap.tech.challenge.purchase.rest.resource.response.PaymentResponse;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface PaymentResponseMapper {

    PaymentResponse toResponse(PaymentDTO dto);
}
