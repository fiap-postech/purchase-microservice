package br.com.fiap.tech.challenge.purchase.adapter.gateway.purchase;

import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchasePaidRepository;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchasePaidGateway;
import br.com.fiap.tech.challenge.purchase.application.mapper.PurchaseMapper;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class PurchasePaidGatewayImpl implements PurchasePaidGateway {

    private final PurchasePaidRepository repository;

    @Override
    public void notify(Purchase purchase) {
        var dto = PurchaseMapper.INSTANCE.toSimpleDTO(purchase);
        repository.notify(dto);
    }

}
