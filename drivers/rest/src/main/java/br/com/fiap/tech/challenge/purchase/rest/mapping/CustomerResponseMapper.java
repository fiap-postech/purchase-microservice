package br.com.fiap.tech.challenge.purchase.rest.mapping;

import br.com.fiap.tech.challenge.purchase.application.dto.CustomerDTO;
import br.com.fiap.tech.challenge.purchase.rest.resource.response.CustomerResponse;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CustomerResponseMapper {

    CustomerResponse toResponse(CustomerDTO dto);
}
