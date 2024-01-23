package br.com.fiap.tech.challenge.purchase.driver.payment.consumer.messaging;

import br.com.fiap.tech.challenge.purchase.adapter.controller.purchase.CreatePurchaseController;
import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseInputDTO;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static br.com.fiap.tech.challenge.purchase.driver.payment.consumer.config.EnvironmentProperties.PAYMENT_DONE_QUEUE;

@RequiredArgsConstructor
@Component
public class PaymentConsumerListener {

    private final CreatePurchaseController controller;

    @SqsListener("${" + PAYMENT_DONE_QUEUE + "}")
    public void listen(Message<PurchaseInputDTO> message) {
        controller.create(message.getPayload());
    }

}
