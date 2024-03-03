package br.com.fiap.tech.challenge.purchase.adapter.gateway.purchase;

import br.com.fiap.tech.challenge.purchase.adapter.repository.CustomerRemovedRepository;
import br.com.fiap.tech.challenge.purchase.application.dto.RemoveCustomerDataDTO;
import br.com.fiap.tech.challenge.purchase.application.gateway.CustomerRemovedGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomerRemovedGatewayImpl implements CustomerRemovedGateway {

    private final CustomerRemovedRepository repository;

    @Override
    public void publish(RemoveCustomerDataDTO dto) {
        repository.publish(dto);
    }
}
