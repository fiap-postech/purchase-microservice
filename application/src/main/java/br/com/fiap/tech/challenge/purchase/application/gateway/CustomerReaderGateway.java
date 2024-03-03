package br.com.fiap.tech.challenge.purchase.application.gateway;

import br.com.fiap.tech.challenge.purchase.enterprise.entity.Customer;

import java.util.Optional;

public interface CustomerReaderGateway {

    Optional<Customer> read(String document);

}
