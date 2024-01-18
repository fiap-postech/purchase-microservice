package br.com.fiap.tech.challenge.purchase.driven.mysql.service;

import br.com.fiap.tech.challenge.purchase.driven.mysql.mapping.DBComboProductMapper;
import br.com.fiap.tech.challenge.purchase.driven.mysql.mapping.DBSingleProductMapper;
import br.com.fiap.tech.challenge.purchase.driven.mysql.model.ComboEntity;
import br.com.fiap.tech.challenge.purchase.driven.mysql.model.ProductEntity;
import br.com.fiap.tech.challenge.purchase.driven.mysql.repository.ProductEntityRepository;
import br.com.fiap.tech.challenge.purchase.application.dto.ProductDTO;
import br.com.fiap.tech.challenge.adapter.repository.ProductReaderRepository;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.ProductCategory;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.purchase.application.util.Page;
import br.com.fiap.tech.challenge.purchase.application.util.ResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static br.com.fiap.tech.challenge.purchase.enterprise.error.ApplicationError.PRODUCT_NOT_FOUND_BY_UUID;

@Service
@RequiredArgsConstructor
public class DBProductEntityReaderRepositoryImpl implements ProductReaderRepository {

    private final DBComboProductMapper comboMapper;
    private final DBSingleProductMapper singleMapper;
    private final ProductEntityRepository repository;

    @Override
    public ResponseList<ProductDTO> readAll(Page page) {
        return readAll(page, repository::findAllByEnabledTrue);
    }

    @Override
    public ResponseList<ProductDTO> readAllByCategory(ProductCategory category, Page page) {
        return readAll(page, pageable -> repository.findAllByEnabledTrueAndCategory(category, pageable));
    }

    @Override
    public ProductDTO readById(String id) {
        return repository.findByUuid(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ApplicationException(PRODUCT_NOT_FOUND_BY_UUID, id));
    }

    private ResponseList<ProductDTO> readAll(Page page, Function<Pageable, org.springframework.data.domain.Page<ProductEntity>> reader) {
        var result = reader.apply(PageRequest.of(page.number(), page.size()));

        return new ResponseList<>(
                result.getNumber(),
                result.getSize(),
                result.getNumberOfElements(),
                result.getTotalElements(),
                result.getContent().stream().map(this::toDTO).toList()
        );
    }

    private ProductDTO toDTO(ProductEntity entity) {
        if (entity instanceof ComboEntity combo) {
            return comboMapper.toDTO(combo);
        }

        return singleMapper.toDTO(entity);

    }
}
