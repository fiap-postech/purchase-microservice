package br.com.fiap.tech.challenge.purchase.application.usecase.purchase;

import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseCreatedGateway;
import br.com.fiap.tech.challenge.purchase.application.mapper.PurchaseMapper;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
class PostPurchaseToManufactureUseCaseImpl implements PostPurchaseToManufactureUseCase {

    private final PurchaseCreatedGateway gateway;

    @Override
    public void post(Purchase purchase) {
        var dto = PurchaseMapper.INSTANCE.toSimpleDTO(purchase);
        dto.setCode(generateCode());

        gateway.notify(purchase);
    }


    private String generateCode() {
        return UUID.randomUUID().toString().substring(0, 4);
    }
}
