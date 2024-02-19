package br.com.fiap.tech.challenge.purchase.application.gateway;

import br.com.fiap.tech.challenge.purchase.application.util.ResponseList;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.purchase.application.util.Page;

import java.util.UUID;

public interface PurchaseReaderGateway {
    ResponseList<Purchase> readAll(Page page);

    Purchase readById(UUID id);

    boolean existsByExternalId(String id);
}
