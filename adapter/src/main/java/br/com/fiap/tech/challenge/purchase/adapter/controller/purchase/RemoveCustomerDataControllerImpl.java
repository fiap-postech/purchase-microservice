package br.com.fiap.tech.challenge.purchase.adapter.controller.purchase;

import br.com.fiap.tech.challenge.purchase.application.dto.RemoveCustomerDataDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.RequestRemoveCustomerDataDTO;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.RemoveCustomerDataUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class RemoveCustomerDataControllerImpl implements RemoveCustomerDataController {

    private final RemoveCustomerDataUseCase useCase;

    @Override
    public RemoveCustomerDataDTO remove(RequestRemoveCustomerDataDTO dto) {
        //TODO publish response to customer
        return useCase.remove(dto);
    }
}
