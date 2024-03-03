package br.com.fiap.tech.challenge.purchase.application.usecase.purchase;

import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.purchase.application.dto.CreatePurchaseDTO;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseReaderGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseWriterGateway;
import br.com.fiap.tech.challenge.purchase.application.mapper.CustomerMapper;
import br.com.fiap.tech.challenge.purchase.application.mapper.PaymentMapper;
import br.com.fiap.tech.challenge.purchase.application.mapper.PurchaseItemMapper;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus;
import lombok.RequiredArgsConstructor;

import static br.com.fiap.tech.challenge.purchase.enterprise.error.ApplicationError.PURCHASE_DUPLICATED;

@RequiredArgsConstructor
class CreatePurchaseUseCaseImpl implements CreatePurchaseUseCase {

    private final PurchaseReaderGateway readerGateway;
    private final PurchaseWriterGateway writerGateway;

    @Override
    public Purchase create(CreatePurchaseDTO dto) {
        if (readerGateway.existsByExternalId(dto.getExternalId())) {
            throw new ApplicationException(PURCHASE_DUPLICATED);
        }

        return writerGateway.write(build(dto));
    }

    private Purchase build(CreatePurchaseDTO dto) {
        return Purchase.builder()
                .payment(PaymentMapper.INSTANCE.toDomain(dto.getPayment()))
                .date(dto.getDate())
                .customer(CustomerMapper.INSTANCE.toDomain(dto.getCustomer()))
                .status(PurchaseStatus.CREATED)
                .externalId(dto.getExternalId())
                .items(dto.getItems().stream().map(PurchaseItemMapper.INSTANCE::toDomain).toList())
                .build();
    }
}
