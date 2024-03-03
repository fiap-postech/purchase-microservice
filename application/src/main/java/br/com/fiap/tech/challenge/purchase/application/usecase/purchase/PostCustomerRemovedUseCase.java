package br.com.fiap.tech.challenge.purchase.application.usecase.purchase;

import br.com.fiap.tech.challenge.purchase.application.dto.RemoveCustomerDataDTO;

public interface PostCustomerRemovedUseCase {

    void post(RemoveCustomerDataDTO dto);

}
