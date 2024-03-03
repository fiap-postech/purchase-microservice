package br.com.fiap.tech.challenge.purchase.driven.mysql.service;

import br.com.fiap.tech.challenge.purchase.adapter.repository.CustomerReaderRepository;
import br.com.fiap.tech.challenge.purchase.adapter.repository.CustomerWriterRepository;
import br.com.fiap.tech.challenge.purchase.application.dto.FullCustomerDTO;
import br.com.fiap.tech.challenge.purchase.driven.mysql.mapping.DBCustomerMapper;
import br.com.fiap.tech.challenge.purchase.driven.mysql.model.CustomerEntity;
import br.com.fiap.tech.challenge.purchase.driven.mysql.repository.CustomerEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DBCustomerEntityRepositoryImpl implements CustomerReaderRepository, CustomerWriterRepository {

    private final CustomerEntityRepository repository;
    private final DBCustomerMapper mapper;

    @Override
    public Optional<FullCustomerDTO> read(String document) {
        return repository.findByDocument(document)
                .map(mapper::toDTO);
    }

    @Override
    public FullCustomerDTO write(FullCustomerDTO customer) {
        return repository.findByUuid(customer.getId())
                .map(e -> update(e, customer))
                .orElseGet(() -> create(customer));
    }

    private FullCustomerDTO update(CustomerEntity entity, FullCustomerDTO dto) {
        entity.setDocument(dto.getDocument());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setEnabled(dto.isEnabled());

        return mapper.toDTO(repository.save(entity));
    }

    private FullCustomerDTO create(FullCustomerDTO dto) {
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }
}
