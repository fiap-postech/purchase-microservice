package br.com.fiap.tech.challenge.purchase.adapter.gateway.purchase;

import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchaseCreatedRepository;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseCreatedGateway;
import br.com.fiap.tech.challenge.purchase.application.mapper.PurchaseMapper;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class PurchaseCreatedGatewayImpl implements PurchaseCreatedGateway {

    private final PurchaseCreatedRepository repository;

    @Override
    public void notify(Purchase purchase) {
        repository.notify(PurchaseMapper.INSTANCE.toDTO(purchase));
    }
}
