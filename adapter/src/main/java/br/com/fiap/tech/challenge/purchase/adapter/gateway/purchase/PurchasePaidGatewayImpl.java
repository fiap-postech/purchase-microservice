package br.com.fiap.tech.challenge.purchase.adapter.gateway.purchase;

import br.com.fiap.tech.challenge.purchase.application.gateway.PurchasePaidGateway;
import br.com.fiap.tech.challenge.purchase.application.mapper.PurchaseMapper;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
class PurchasePaidGatewayImpl implements PurchasePaidGateway {

    @Override
    public void notify(Purchase purchase) {
        var dto = PurchaseMapper.INSTANCE.toSimpleDTO(purchase);
        dto.setCode(generateCode());
    }

    private String generateCode() {
        return UUID.randomUUID().toString().substring(0, 4);
    }
}
