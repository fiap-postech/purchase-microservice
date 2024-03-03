package br.com.fiap.tech.challenge.purchase.adapter.gateway.purchase;

import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchaseCreatedRepository;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseCreatedGateway;
import br.com.fiap.tech.challenge.purchase.application.mapper.PurchaseMapper;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import lombok.RequiredArgsConstructor;

import static br.com.fiap.tech.challenge.purchase.adapter.util.FullPurchaseItemGroup.groupEqualItems;

@RequiredArgsConstructor
class PurchaseCreatedGatewayImpl implements PurchaseCreatedGateway {

    private final PurchaseCreatedRepository repository;

    @Override
    public void notify(Purchase purchase) {
        var dto = groupEqualItems(PurchaseMapper.INSTANCE.toDTO(purchase));

        repository.notify(dto);
    }
}
