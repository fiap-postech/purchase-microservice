package br.com.fiap.tech.challenge.purchase.rest.mapping;

import br.com.fiap.tech.challenge.application.dto.PaymentConfirmDTO;
import br.com.fiap.tech.challenge.purchase.rest.resource.request.PaymentConfirmRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface PaymentConfirmMapper {

    @Mapping(target = "apiVersion", source = "api_version")
    PaymentConfirmDTO toDTO(PaymentConfirmRequest request);
}
