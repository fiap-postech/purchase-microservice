package br.com.fiap.tech.challenge.purchase.driven.mysql.mapping;

import br.com.fiap.tech.challenge.purchase.driven.mysql.model.ComboEntity;
import br.com.fiap.tech.challenge.purchase.driven.mysql.model.ProductEntity;
import br.com.fiap.tech.challenge.purchase.driven.mysql.model.PurchaseItemEntity;
import br.com.fiap.tech.challenge.purchase.driven.mysql.repository.ProductEntityRepository;
import br.com.fiap.tech.challenge.purchase.application.dto.ProductDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.PurchaseItemDTO;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static br.com.fiap.tech.challenge.purchase.enterprise.error.ApplicationError.PRODUCT_NOT_FOUND_BY_UUID;
import static java.util.Objects.isNull;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public abstract class DBPurchaseItemMapper {

    @Autowired
    protected ProductEntityRepository productRepository;

    @Autowired
    private DBComboProductMapper comboProductMapper;

    @Autowired
    private DBSingleProductMapper singleProductMapper;

    @Mapping(target = "product", source = "source", qualifiedByName = "getProductEntity")
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "lastUpdated", ignore = true)
    @Mapping(target = "purchase", ignore = true)
    @Mapping(target = "version", ignore = true)
    public abstract PurchaseItemEntity toEntity(PurchaseItemDTO source);

    @Mapping(target = "fullPrice", source = "source", qualifiedByName = "getPrice")
    @Mapping(target = "product", source = "source", qualifiedByName = "getProduct")
    public abstract PurchaseItemDTO toDTO(PurchaseItemEntity source);

    @Named("getPrice")
    BigDecimal getPrice(PurchaseItemEntity entity) {
        return entity.getProduct().getPrice();
    }

    @Named("getProductEntity")
    ProductEntity getProductEntity(PurchaseItemDTO purchaseItem) {
        var product = purchaseItem.getProduct();
        var uuid = product.getId();

        return productRepository.findByUuid(uuid)
                .orElseThrow(() -> new ApplicationException(PRODUCT_NOT_FOUND_BY_UUID, uuid));
    }

    @Named("getProduct")
    ProductDTO getProduct(PurchaseItemEntity purchaseItemEntity) {
        var productEntity = purchaseItemEntity.getProduct();

        if (isNull(productEntity)) return null;

        if (productEntity instanceof ComboEntity comboEntity){
            return comboProductMapper.toDTO(comboEntity);
        }

        return singleProductMapper.toDTO(productEntity);
    }
}
