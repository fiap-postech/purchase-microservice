package br.com.fiap.tech.challenge.purchase.application.usecase.purchase;

import br.com.fiap.tech.challenge.purchase.application.dto.CreatePurchaseDTO;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;

public interface CreatePurchaseUseCase {
    Purchase create(CreatePurchaseDTO dto);
}
