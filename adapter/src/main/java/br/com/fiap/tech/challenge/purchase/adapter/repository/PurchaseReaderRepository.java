package br.com.fiap.tech.challenge.purchase.adapter.repository;

import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.purchase.application.util.Page;
import br.com.fiap.tech.challenge.purchase.application.util.ResponseList;

public interface PurchaseReaderRepository {

    ResponseList<PurchaseDTO> readAll(Page page);

    PurchaseDTO readById(String id);

}
