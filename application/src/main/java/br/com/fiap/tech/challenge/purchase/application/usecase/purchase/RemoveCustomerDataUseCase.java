package br.com.fiap.tech.challenge.purchase.application.usecase.purchase;

import br.com.fiap.tech.challenge.purchase.application.dto.RemoveCustomerDataDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.RequestRemoveCustomerDataDTO;

public interface RemoveCustomerDataUseCase {

    RemoveCustomerDataDTO remove(RequestRemoveCustomerDataDTO dto);

}
