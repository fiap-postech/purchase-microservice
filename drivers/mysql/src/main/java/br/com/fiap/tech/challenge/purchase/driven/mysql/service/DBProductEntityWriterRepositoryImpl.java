package br.com.fiap.tech.challenge.purchase.driven.mysql.service;

import br.com.fiap.tech.challenge.purchase.driven.mysql.mapping.DBComboProductMapper;
import br.com.fiap.tech.challenge.purchase.driven.mysql.mapping.DBSingleProductMapper;
import br.com.fiap.tech.challenge.purchase.driven.mysql.model.ComboEntity;
import br.com.fiap.tech.challenge.purchase.driven.mysql.model.ProductEntity;
import br.com.fiap.tech.challenge.purchase.driven.mysql.repository.ProductEntityRepository;
import br.com.fiap.tech.challenge.purchase.application.dto.ComboDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.ProductDTO;
import br.com.fiap.tech.challenge.adapter.repository.ProductWriterRepository;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static br.com.fiap.tech.challenge.purchase.enterprise.error.ApplicationError.PRODUCT_NOT_FOUND_BY_UUID;

@Service
@RequiredArgsConstructor
public class DBProductEntityWriterRepositoryImpl implements ProductWriterRepository {

    private final ProductEntityRepository repository;
    private final DBComboProductMapper comboProductMapper;
    private final DBSingleProductMapper singleProductMapper;

    @Override
    public ProductDTO save(ProductDTO product) {
        if (repository.existsByUuid(product.getId())) {
            return update(product);
        }

        return insert(product);
    }

    private ProductDTO insert(ProductDTO product) {
        var productEntity = toProductEntity(product);
        decorateIfCombo(product, productEntity);
        return toDTO(repository.save(productEntity));
    }

    private void decorateIfCombo(ProductDTO product, ProductEntity productEntity) {
        if (productEntity instanceof ComboEntity combo) {
            var comboDTO = (ComboDTO) product;

            combo.setBeverage(getByUUID(comboDTO.getBeverage().getId()));
            combo.setSandwich(getByUUID(comboDTO.getSandwich().getId()));
            combo.setSideDish(getByUUID(comboDTO.getSideDish().getId()));
        }
    }

    private ProductDTO update(ProductDTO product) {
        var entity = getByUUID(product.getId());

        entity.setCategory(product.getCategory());
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        entity.setImage(product.getImage());
        entity.setEnabled(product.getEnabled());
        entity.setDescription(product.getDescription());

        return toDTO(repository.save(entity));
    }

    private ProductEntity getByUUID(String uuid) {
        return repository.findByUuid(uuid).orElseThrow(() -> new ApplicationException(PRODUCT_NOT_FOUND_BY_UUID, uuid));
    }

    public ProductEntity toProductEntity(ProductDTO product) {
        if (product instanceof ComboDTO combo) {
            return  comboProductMapper.toEntity(combo);
        }

        return singleProductMapper.toEntity(product);
    }

    public ProductDTO toDTO(ProductEntity product) {
        if (product instanceof ComboEntity combo) {
            return  comboProductMapper.toDTO(combo);
        }

        return singleProductMapper.toDTO(product);
    }
}
