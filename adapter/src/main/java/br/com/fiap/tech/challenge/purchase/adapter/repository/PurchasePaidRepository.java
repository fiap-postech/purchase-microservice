package br.com.fiap.tech.challenge.purchase.adapter.repository;

import br.com.fiap.tech.challenge.purchase.application.dto.SimplePurchaseDTO;

public interface PurchasePaidRepository {

    void notify(SimplePurchaseDTO dto);

}
