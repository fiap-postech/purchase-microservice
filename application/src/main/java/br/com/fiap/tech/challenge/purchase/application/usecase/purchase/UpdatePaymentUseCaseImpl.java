package br.com.fiap.tech.challenge.purchase.application.usecase.purchase;

import br.com.fiap.tech.challenge.purchase.application.dto.UpdatePaymentDTO;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseReaderGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseWriterGateway;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Payment;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import static br.com.fiap.tech.challenge.purchase.enterprise.enums.PaymentStatus.PAID;

@RequiredArgsConstructor
class UpdatePaymentUseCaseImpl implements UpdatePaymentUseCase {

    private final PurchaseReaderGateway readerGateway;
    private final PurchaseWriterGateway writerGateway;

    @Override
    public Purchase update(UpdatePaymentDTO dto) {
        var purchase = readerGateway.readById(UUID.fromString(dto.getPurchaseId()));

        purchase = purchase.toBuilder().payment(buildPayment(dto)).build();
        purchase = dto.getStatus() == PAID ? purchase.paidSuccessful() : purchase.paidFail();

        return writerGateway.write(purchase);
    }

    private Payment buildPayment(UpdatePaymentDTO dto) {
        return Payment.builder()
                .id(dto.getId())
                .status(dto.getStatus())
                .build();
    }
}
