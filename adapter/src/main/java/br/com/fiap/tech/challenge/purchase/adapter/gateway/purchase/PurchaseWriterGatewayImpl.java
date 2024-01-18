package br.com.fiap.tech.challenge.purchase.adapter.gateway.purchase;

import br.com.fiap.tech.challenge.purchase.adapter.mapping.PurchaseMapper;
import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchaseWriterRepository;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseWriterGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class PurchaseWriterGatewayImpl implements PurchaseWriterGateway {

    private final PurchaseWriterRepository repository;

    @Override
    public Purchase write(Purchase purchase) {
        var mapper = PurchaseMapper.INSTANCE;

        return mapper.toDomain(repository.write(mapper.toDTO(purchase)));
    }
}
