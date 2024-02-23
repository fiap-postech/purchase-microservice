package br.com.fiap.tech.challenge.purchase.launcher.messaging;

import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseNotification;
import br.com.fiap.tech.challenge.purchase.application.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.SimplePurchaseDTO;
import br.com.fiap.tech.challenge.purchase.launcher.service.QueueService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PurchaseTestListener {

    private final QueueService service;

    @SqsListener("local-payment-purchase-created-queue")
    public void listenCreatedQueue(Message<PurchaseDTO> message) {
        service.received(message.getPayload());
    }

    @SqsListener("local-manufacture-purchase-paid-queue")
    public void listenPaidQueue(Message<SimplePurchaseDTO> message) {
        service.received(message.getPayload());
    }

    @SqsListener("local-notification-purchase-status-queue")
    public void listenStatusQueue(Message<PurchaseNotification> message) {
        service.received(message.getPayload());
    }
}
