package br.com.fiap.tech.challenge.purchase.adapter.gateway.purchase;

import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchaseWriterRepository;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseWriterGateway;
import br.com.fiap.tech.challenge.purchase.application.mapper.PurchaseMapper;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
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
