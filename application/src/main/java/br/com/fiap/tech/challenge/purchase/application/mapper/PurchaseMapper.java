package br.com.fiap.tech.challenge.purchase.application.mapper;

import br.com.fiap.tech.challenge.purchase.application.dto.CustomerDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.PaymentDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.PurchaseItemDTO;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = { CommonMapper.class, PurchaseItemMapper.class, CustomerMapper.class, PaymentMapper.class })
public interface PurchaseMapper {

    PurchaseMapper INSTANCE = Mappers.getMapper(PurchaseMapper.class);

    @Mapping(target = "uuid", source = "id", qualifiedByName = "generateUuid")
    Purchase toDomain(PurchaseDTO dto);

    @Mapping(target = "id", expression = "java(purchase.uuid().toString())")
    @Mapping(target = "customer", source = "purchase", qualifiedByName = "getCustomerDTO")
    @Mapping(target = "status", expression = "java(purchase.status())")
    @Mapping(target = "date", expression = "java(purchase.date())")
    @Mapping(target = "items", source = "purchase", qualifiedByName = "getPurchaseItems")
    @Mapping(target = "payment", source = "purchase", qualifiedByName = "getPaymentDTO")
    PurchaseDTO toDTO(Purchase purchase);

    @Named("getCustomerDTO")
    static CustomerDTO getCustomerDTO(Purchase purchase) {
        return CustomerMapper.INSTANCE.toDTO(purchase.customer());
    }

    @Named("getPurchaseItems")
    static List<PurchaseItemDTO> getPurchaseItems(Purchase purchase) {
        return purchase.items().stream()
                .map(PurchaseItemMapper.INSTANCE::toDTO)
                .toList();
    }

    @Named("getPaymentDTO")
    static PaymentDTO getPaymentDTO(Purchase purchase) {
        return PaymentMapper.INSTANCE.toDTO(purchase.payment());
    }
}
