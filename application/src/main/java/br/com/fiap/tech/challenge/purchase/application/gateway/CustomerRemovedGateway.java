package br.com.fiap.tech.challenge.purchase.application.gateway;

import br.com.fiap.tech.challenge.purchase.application.dto.RemoveCustomerDataDTO;

public interface CustomerRemovedGateway {

    void publish(RemoveCustomerDataDTO dto);

}
