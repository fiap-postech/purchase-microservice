package br.com.fiap.tech.challenge.purchase.adapter.gateway.purchase;

import br.com.fiap.tech.challenge.purchase.adapter.repository.CustomerReaderRepository;
import br.com.fiap.tech.challenge.purchase.adapter.repository.CustomerWriterRepository;
import br.com.fiap.tech.challenge.purchase.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.CustomerWriterGateway;
import br.com.fiap.tech.challenge.purchase.application.mapper.CustomerMapper;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Customer;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
class CustomerGatewayImpl implements CustomerReaderGateway, CustomerWriterGateway {

    private final CustomerReaderRepository readerRepository;
    private final CustomerWriterRepository writerRepository;

    @Override
    public Optional<Customer> read(String document) {
        return readerRepository.read(document)
                .map(CustomerMapper.INSTANCE::toDomain);
    }

    @Override
    public Customer write(Customer customer) {
        var mapper = CustomerMapper.INSTANCE;

        return mapper.toDomain(writerRepository.write(mapper.toDTO(customer)));
    }
}
