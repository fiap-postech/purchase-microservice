package br.com.fiap.tech.challenge.purchase.driven.mysql.mapping;

import br.com.fiap.tech.challenge.purchase.application.dto.FullPurchaseItemDTO;
import br.com.fiap.tech.challenge.purchase.driven.mysql.model.ProductEntity;
import br.com.fiap.tech.challenge.purchase.driven.mysql.model.PurchaseItemEntity;
import br.com.fiap.tech.challenge.purchase.driven.mysql.repository.ProductEntityRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = { DBProductMapper.class })
public abstract class DBPurchaseItemMapper {

    @Autowired
    protected ProductEntityRepository productRepository;

    @Autowired
    protected DBProductMapper productMapper;

    @Mapping(target = "product", source = "source", qualifiedByName = "getProductEntity")
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "lastUpdated", ignore = true)
    @Mapping(target = "purchase", ignore = true)
    @Mapping(target = "version", ignore = true)
    public abstract PurchaseItemEntity toEntity(FullPurchaseItemDTO source);

    public abstract FullPurchaseItemDTO toDTO(PurchaseItemEntity source);

    @Named("getProductEntity")
    ProductEntity getProductEntity(FullPurchaseItemDTO purchaseItem) {
        var product = purchaseItem.getProduct();
        var uuid = product.getId();

        return productRepository.findByUuid(uuid)
                .orElseGet(() -> productRepository.save(productMapper.toEntity(purchaseItem.getProduct())));
    }
}
