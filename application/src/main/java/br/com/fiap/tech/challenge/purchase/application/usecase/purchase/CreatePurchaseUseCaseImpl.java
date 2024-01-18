package br.com.fiap.tech.challenge.purchase.application.usecase.purchase;

import br.com.fiap.tech.challenge.purchase.application.dto.CreatePurchaseDTO;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseWriterGateway;
import br.com.fiap.tech.challenge.purchase.application.mapper.CreatePurchaseMapper;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class CreatePurchaseUseCaseImpl implements CreatePurchaseUseCase {

    private final PurchaseWriterGateway writer;

    @Override
    public Purchase create(CreatePurchaseDTO dto) {
        return writer.write(CreatePurchaseMapper.INSTANCE.toDomain(dto));
    }
}
