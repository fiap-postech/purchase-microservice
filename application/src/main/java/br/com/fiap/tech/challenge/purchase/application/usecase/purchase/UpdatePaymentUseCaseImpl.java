package br.com.fiap.tech.challenge.purchase.application.usecase.purchase;

import br.com.fiap.tech.challenge.purchase.application.dto.UpdatePaymentDTO;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseReaderGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseWriterGateway;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Payment;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import static br.com.fiap.tech.challenge.purchase.enterprise.enums.PaymentStatus.CREATED;
import static br.com.fiap.tech.challenge.purchase.enterprise.enums.PaymentStatus.PAID;

@RequiredArgsConstructor
class UpdatePaymentUseCaseImpl implements UpdatePaymentUseCase {

    private final PurchaseReaderGateway readerGateway;
    private final PurchaseWriterGateway writerGateway;

    @Override
    public Purchase update(UpdatePaymentDTO dto) {
        var purchase = readerGateway.readById(UUID.fromString(dto.getPurchaseId()));

        if (dto.getStatus() == CREATED) {
            return onCreatedPayment(purchase, dto);
        }

        return onDonePayment(purchase, dto);
    }

    private Purchase onCreatedPayment(Purchase purchase, UpdatePaymentDTO dto) {
        purchase = purchase.toBuilder()
                .payment(buildPayment(dto))
                .build();

        return writerGateway.write(purchase.waitingPayment());
    }

    private Purchase onDonePayment(Purchase purchase, UpdatePaymentDTO dto) {
        var payment = purchase.payment()
                .toBuilder()
                .status(dto.getStatus())
                .build();

        purchase = purchase.toBuilder()
                .payment(payment)
                .build();

        if (purchase.payment().status() == PAID) {
            purchase = writerGateway.write(purchase.paidSuccessful());
            return writerGateway.write(purchase.waitMake());
        } else {
            return writerGateway.write(purchase.paidFail());
        }
    }

    private Payment buildPayment(UpdatePaymentDTO dto) {
        return Payment.builder()
                .id(dto.getId())
                .status(dto.getStatus())
                .url(dto.getPaymentUrl())
                .build();
    }
}
