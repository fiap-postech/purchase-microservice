package br.com.fiap.tech.challenge.purchase.application.mapper;

import br.com.fiap.tech.challenge.purchase.application.dto.FullProductDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.SimpleProductDTO;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static br.com.fiap.tech.challenge.util.Mappings.priceToBigDecimalConverter;

@Mapper(uses = {CommonMapper.class})
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "uuid", source = "id", qualifiedByName = "generateUuid")
    @Mapping(target = "price", source = "price", qualifiedByName = "getPrice")
    Product toDomain(FullProductDTO dto);

    @Mapping(target = "id", expression = "java(source.uuid().toString())")
    @Mapping(target = "price", source = "source", qualifiedByName = "priceToBigDecimal")
    @Mapping(target = "name", expression = "java(source.name())")
    @Mapping(target = "description", expression = "java(source.description())")
    FullProductDTO toDTO(Product source);

    @Mapping(target = "id", expression = "java(source.uuid().toString())")
    @Mapping(target = "name", expression = "java(source.name())")
    @Mapping(target = "description", expression = "java(source.description())")
    SimpleProductDTO toSimpleDTO(Product source);

    @Named("priceToBigDecimal")
    static BigDecimal priceToBigDecimal(Product product) {
        return priceToBigDecimalConverter(product.price());
    }
}
