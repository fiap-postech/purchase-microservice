package br.com.fiap.tech.challenge.purchase.rest.mapping;

import br.com.fiap.tech.challenge.purchase.application.dto.FullProductDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.FullPurchaseItemDTO;
import br.com.fiap.tech.challenge.purchase.rest.resource.response.ProductResponse;
import br.com.fiap.tech.challenge.purchase.rest.resource.response.PurchaseItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public abstract class PurchaseItemResponseMapper {

    @Autowired
    private ProductResponseMapper productResponseMapper;

    @Mapping(target = "product", source = "product", qualifiedByName = "getProductResponse")
    public abstract PurchaseItemResponse toResponse(FullPurchaseItemDTO dto);

    @Named("getProductResponse")
    ProductResponse getProductResponse(FullProductDTO product) {
        return productResponseMapper.toResponse(product);
    }

}
