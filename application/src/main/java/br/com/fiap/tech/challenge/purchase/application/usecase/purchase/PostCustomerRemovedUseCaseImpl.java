package br.com.fiap.tech.challenge.purchase.application.usecase.purchase;

import br.com.fiap.tech.challenge.purchase.application.dto.RemoveCustomerDataDTO;
import br.com.fiap.tech.challenge.purchase.application.gateway.CustomerRemovedGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class PostCustomerRemovedUseCaseImpl implements PostCustomerRemovedUseCase {

    private final CustomerRemovedGateway gateway;

    @Override
    public void post(RemoveCustomerDataDTO dto) {
        gateway.publish(dto);
    }
}
