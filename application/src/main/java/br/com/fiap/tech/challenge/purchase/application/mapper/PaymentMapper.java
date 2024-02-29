package br.com.fiap.tech.challenge.purchase.application.mapper;

import br.com.fiap.tech.challenge.purchase.application.dto.PaymentDTO;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { CommonMapper.class })
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    @Mapping(target = "id", expression = "java(payment.uuid().toString())")
    @Mapping(target = "status", expression = "java(payment.status())")
    @Mapping(target = "created", expression = "java(payment.created())")
    @Mapping(target = "paymentId", expression = "java(payment.id())")
    @Mapping(target = "paymentUrl", expression = "java(payment.url())")
    PaymentDTO toDTO(Payment payment);

    @Mapping(target = "uuid", source = "id", qualifiedByName = "generateUuid")
    @Mapping(target = "url", source = "paymentUrl")
    Payment toDomain(PaymentDTO dto);

}
