package br.com.fiap.tech.challenge.purchase.application.usecase.purchase;

import br.com.fiap.tech.challenge.purchase.application.dto.CreatePurchaseDTO;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseCreatedGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseWriterGateway;
import br.com.fiap.tech.challenge.purchase.application.mapper.CustomerMapper;
import br.com.fiap.tech.challenge.purchase.application.mapper.PaymentMapper;
import br.com.fiap.tech.challenge.purchase.application.mapper.PurchaseItemMapper;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class CreatePurchaseUseCaseImpl implements CreatePurchaseUseCase {

    private final PurchaseWriterGateway writerGateway;
    private final PurchaseCreatedGateway createdGateway;

    @Override
    public Purchase create(CreatePurchaseDTO dto) {
        var purchase = writerGateway.write(build(dto));

        createdGateway.notify(purchase);
        return purchase;
    }

    private Purchase build(CreatePurchaseDTO dto) {
        return Purchase.builder()
                .payment(PaymentMapper.INSTANCE.toDomain(dto.getPayment()))
                .date(dto.getDate())
                .customer(CustomerMapper.INSTANCE.toDomain(dto.getCustomer()))
                .status(dto.getStatus())
                .items(dto.getItems().stream().map(PurchaseItemMapper.INSTANCE::toDomain).toList())
                .build();
    }
}
