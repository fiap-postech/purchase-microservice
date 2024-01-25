package br.com.fiap.tech.challenge.purchase.adapter.repository;

import br.com.fiap.tech.challenge.purchase.application.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.SimplePurchaseDTO;

public interface PurchaseCreatedRepository {

    void notify(SimplePurchaseDTO dto);

}
