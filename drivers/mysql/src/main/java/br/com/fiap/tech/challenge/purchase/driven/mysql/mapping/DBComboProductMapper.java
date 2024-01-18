package br.com.fiap.tech.challenge.purchase.driven.mysql.mapping;

import br.com.fiap.tech.challenge.purchase.driven.mysql.model.ComboEntity;
import br.com.fiap.tech.challenge.purchase.application.dto.ComboDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING,
        uses = { DBSingleProductMapper.class }
)
public interface DBComboProductMapper {

    @Mapping(target = "id", source = "uuid")
    ComboDTO toDTO(ComboEntity source);

    @Mapping(target = "uuid", source = "id")
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "lastUpdated", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    ComboEntity toEntity(ComboDTO combo);
}
