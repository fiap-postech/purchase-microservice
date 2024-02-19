package br.com.fiap.tech.challenge.purchase.driven.mysql.mapping;

import br.com.fiap.tech.challenge.purchase.application.dto.PaymentDTO;
import br.com.fiap.tech.challenge.purchase.driven.mysql.model.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface DBPaymentMapper {
    @Mapping(target = "id", source = "uuid")
    PaymentDTO toDTO(PaymentEntity payment);

    @Mapping(target = "created", ignore = true)
    @Mapping(target = "lastUpdated", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "purchase", ignore = true)
    @Mapping(target = "version", ignore = true)
    PaymentEntity toEntity(PaymentDTO payment);

}
