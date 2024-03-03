package br.com.fiap.tech.challenge.purchase.adapter.controller.purchase;

import br.com.fiap.tech.challenge.purchase.application.dto.RemoveCustomerDataDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.RequestRemoveCustomerDataDTO;

public interface RemoveCustomerDataController {

    RemoveCustomerDataDTO remove(RequestRemoveCustomerDataDTO dto);

}
