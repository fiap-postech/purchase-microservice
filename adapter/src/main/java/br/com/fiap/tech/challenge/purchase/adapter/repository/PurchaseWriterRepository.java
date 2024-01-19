package br.com.fiap.tech.challenge.purchase.adapter.repository;

import br.com.fiap.tech.challenge.purchase.application.dto.PurchaseDTO;

public interface PurchaseWriterRepository {
    PurchaseDTO write(PurchaseDTO purchase);
}
