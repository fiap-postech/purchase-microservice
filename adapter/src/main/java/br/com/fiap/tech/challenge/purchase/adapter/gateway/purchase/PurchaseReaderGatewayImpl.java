package br.com.fiap.tech.challenge.purchase.adapter.gateway.purchase;

import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchaseReaderRepository;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseReaderGateway;
import br.com.fiap.tech.challenge.purchase.application.mapper.PurchaseMapper;
import br.com.fiap.tech.challenge.purchase.application.util.Page;
import br.com.fiap.tech.challenge.purchase.application.util.ResponseList;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
class PurchaseReaderGatewayImpl implements PurchaseReaderGateway {
    private final PurchaseReaderRepository repository;

    @Override
    public ResponseList<Purchase> readAll(Page page) {
        return ResponseList.from(
                repository.readAll(page),
                PurchaseMapper.INSTANCE::toDomain
        );
    }

    @Override
    public Purchase readById(UUID id) {
        return PurchaseMapper.INSTANCE.toDomain(repository.readById(id.toString()));
    }

    @Override
    public boolean existsByExternalId(String id) {
        return repository.existsByExternalId(id);
    }
}
