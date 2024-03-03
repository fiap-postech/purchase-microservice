package br.com.fiap.tech.challenge.purchase.adapter.repository;

import br.com.fiap.tech.challenge.purchase.application.dto.FullCustomerDTO;

import java.util.Optional;

public interface CustomerReaderRepository {

    Optional<FullCustomerDTO> read(String document);

}
