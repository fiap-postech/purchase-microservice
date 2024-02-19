package br.com.fiap.tech.challenge.purchase.application.mapper;

import br.com.fiap.tech.challenge.purchase.application.dto.FullCustomerDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.SimpleCustomerDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.SimplePurchaseDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.PaymentDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.FullPurchaseItemDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.SimplePurchaseItemDTO;
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
    @Mapping(target = "externalId", expression = "java(purchase.externalId())")
    PurchaseDTO toDTO(Purchase purchase);

    @Mapping(target = "id", expression = "java(purchase.uuid().toString())")
    @Mapping(target = "customer", source = "purchase", qualifiedByName = "getSimpleCustomerDTO")
    @Mapping(target = "status", expression = "java(purchase.status())")
    @Mapping(target = "date", expression = "java(purchase.date())")
    @Mapping(target = "items", source = "purchase", qualifiedByName = "getSimplePurchaseItems")
    SimplePurchaseDTO toSimpleDTO(Purchase purchase);

    @Named("getCustomerDTO")
    static FullCustomerDTO getCustomerDTO(Purchase purchase) {
        return CustomerMapper.INSTANCE.toDTO(purchase.customer());
    }

    @Named("getSimpleCustomerDTO")
    static SimpleCustomerDTO getSimpleCustomerDTO(Purchase purchase) {
        return CustomerMapper.INSTANCE.toSimpleDTO(purchase.customer());
    }

    @Named("getPurchaseItems")
    static List<FullPurchaseItemDTO> getPurchaseItems(Purchase purchase) {
        return purchase.items().stream()
                .map(PurchaseItemMapper.INSTANCE::toDTO)
                .toList();
    }

    @Named("getSimplePurchaseItems")
    static List<SimplePurchaseItemDTO> getSimplePurchaseItems(Purchase purchase) {
        return purchase.items().stream()
                .map(PurchaseItemMapper.INSTANCE::toSimpleDTO)
                .toList();
    }

    @Named("getPaymentDTO")
    static PaymentDTO getPaymentDTO(Purchase purchase) {
        return PaymentMapper.INSTANCE.toDTO(purchase.payment());
    }
}
