package br.com.fiap.tech.challenge.purchase.application.gateway;

import br.com.fiap.tech.challenge.purchase.enterprise.entity.Customer;

public interface CustomerWriterGateway {

    Customer write(Customer customer);

}
