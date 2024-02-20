package br.com.fiap.tech.challenge.purchase.application.usecase.purchase;

import br.com.fiap.tech.challenge.purchase.application.dto.UpdatePaymentDTO;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;

public interface UpdatePaymentUseCase {

    Purchase update(UpdatePaymentDTO dto);

}
