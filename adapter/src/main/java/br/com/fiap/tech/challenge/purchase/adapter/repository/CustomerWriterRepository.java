package br.com.fiap.tech.challenge.purchase.adapter.repository;

import br.com.fiap.tech.challenge.purchase.application.dto.FullCustomerDTO;

public interface CustomerWriterRepository {

    FullCustomerDTO write(FullCustomerDTO customer);

}
