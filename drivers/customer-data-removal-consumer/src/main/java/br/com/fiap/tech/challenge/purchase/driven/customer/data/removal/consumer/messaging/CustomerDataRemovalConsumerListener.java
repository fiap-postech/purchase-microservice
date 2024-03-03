package br.com.fiap.tech.challenge.purchase.driven.customer.data.removal.consumer.messaging;

import br.com.fiap.tech.challenge.purchase.adapter.controller.purchase.RemoveCustomerDataController;
import br.com.fiap.tech.challenge.purchase.application.dto.RequestRemoveCustomerDataDTO;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static br.com.fiap.tech.challenge.purchase.driven.customer.data.removal.consumer.config.EnvironmentProperties.CUSTOMER_DATA_REMOVAL_QUEUE;

@RequiredArgsConstructor
@Component
public class CustomerDataRemovalConsumerListener {

    private final RemoveCustomerDataController controller;

    @SqsListener("${" + CUSTOMER_DATA_REMOVAL_QUEUE + "}")
    public void listen(Message<RequestRemoveCustomerDataDTO> message) {
        controller.remove(message.getPayload());
    }

}
