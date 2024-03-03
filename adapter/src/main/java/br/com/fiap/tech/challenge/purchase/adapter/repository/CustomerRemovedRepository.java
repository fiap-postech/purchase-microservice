package br.com.fiap.tech.challenge.purchase.adapter.repository;

import br.com.fiap.tech.challenge.purchase.application.dto.RemoveCustomerDataDTO;

public interface CustomerRemovedRepository {

    void publish(RemoveCustomerDataDTO dto);

}
