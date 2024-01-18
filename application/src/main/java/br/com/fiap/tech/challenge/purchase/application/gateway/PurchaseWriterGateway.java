package br.com.fiap.tech.challenge.purchase.application.gateway;

import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;

public interface PurchaseWriterGateway {
    Purchase write(Purchase purchase);
}
