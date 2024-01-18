package br.com.fiap.tech.challenge.purchase.adapter.mapping;

import br.com.fiap.tech.challenge.purchase.application.dto.PaymentDTO;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

@Mapper(uses = { CommonMapper.class })
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    @Mapping(target = "id", expression = "java(payment.uuid().toString())")
    @Mapping(target = "amount", source = "payment", qualifiedByName = "mapAmountToDTO")
    @Mapping(target = "date", expression = "java(payment.date())")
    @Mapping(target = "status", expression = "java(payment.status())")
    @Mapping(target = "method", expression = "java(payment.method())")
    @Mapping(target = "urlPayment", expression = "java(payment.urlPayment())")
    PaymentDTO toDTO(Payment payment);

    @Mapping(target = "uuid", source = "id", qualifiedByName = "generateUuid")
    @Mapping(target = "amount", source = "amount", qualifiedByName = "mapBigDecimalToMoney")
    Payment toDomain(PaymentDTO dto);

    @Named("mapAmountToDTO")
    static BigDecimal mapAmountToDTO(Payment payment) {
        return CommonMapper.map(payment.amount());
    }

}
