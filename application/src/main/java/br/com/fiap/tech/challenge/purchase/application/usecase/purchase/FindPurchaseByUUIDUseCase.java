package br.com.fiap.tech.challenge.purchase.application.usecase.purchase;

import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;

import java.util.UUID;

public interface FindPurchaseByUUIDUseCase {

    Purchase get(UUID uuid);

}
