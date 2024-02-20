package br.com.fiap.tech.challenge.purchase.driver.payment.consumer.messaging;

import br.com.fiap.tech.challenge.purchase.adapter.controller.purchase.UpdatePaymentController;
import br.com.fiap.tech.challenge.purchase.application.dto.UpdatePaymentDTO;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static br.com.fiap.tech.challenge.purchase.driver.payment.consumer.config.EnvironmentProperties.PAYMENT_DONE_QUEUE;

@RequiredArgsConstructor
@Component
public class PaymentConsumerListener {

    private final UpdatePaymentController controller;

    @SqsListener("${" + PAYMENT_DONE_QUEUE + "}")
    public void listen(Message<UpdatePaymentDTO> message) {
        controller.update(message.getPayload());
    }

}
