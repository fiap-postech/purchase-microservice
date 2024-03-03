package br.com.fiap.tech.challenge.purchase.adapter.controller.purchase;

import br.com.fiap.tech.challenge.purchase.application.dto.RemoveCustomerDataDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.RequestRemoveCustomerDataDTO;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.PostCustomerRemovedUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.RemoveCustomerDataUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
class RemoveCustomerDataControllerImpl implements RemoveCustomerDataController {

    private final RemoveCustomerDataUseCase removeUseCase;
    private final PostCustomerRemovedUseCase postRemovedUseCase;

    @Override
    @Transactional
    public RemoveCustomerDataDTO remove(RequestRemoveCustomerDataDTO dto) {
        var result = removeUseCase.remove(dto);

        postRemovedUseCase.post(result);

        return result;
    }
}
