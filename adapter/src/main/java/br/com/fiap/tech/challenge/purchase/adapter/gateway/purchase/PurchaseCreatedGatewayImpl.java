package br.com.fiap.tech.challenge.purchase.adapter.gateway.purchase;

import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchaseCreatedRepository;
import br.com.fiap.tech.challenge.purchase.application.dto.SimplePurchaseDTO;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseCreatedGateway;
import br.com.fiap.tech.challenge.purchase.application.mapper.PurchaseMapper;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
class PurchaseCreatedGatewayImpl implements PurchaseCreatedGateway {

    private final PurchaseCreatedRepository repository;

    @Override
    public void notify(Purchase purchase) {
        var dto = PurchaseMapper.INSTANCE.toSimpleDTO(purchase);
        dto.setCode(generateCode());

        repository.notify(dto);
    }

    private String generateCode() {
        return UUID.randomUUID().toString().substring(0, 4);
    }
}
