package br.com.fiap.tech.challenge.purchase.adapter.repository;

import br.com.fiap.tech.challenge.purchase.application.dto.PurchaseDTO;

public interface PurchaseCreatedRepository {

    void notify(PurchaseDTO dto);

}
