package br.com.fiap.tech.challenge.purchase.adapter.repository;

import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseNotification;

public interface PurchaseStatusRepository {

    void publish(PurchaseNotification notification);

}
