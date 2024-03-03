package br.com.fiap.tech.challenge.purchase.driven.customer.removed.producer.messaging;

import br.com.fiap.tech.challenge.purchase.adapter.repository.CustomerRemovedRepository;
import br.com.fiap.tech.challenge.purchase.application.dto.RemoveCustomerDataDTO;
import br.com.fiap.tech.challenge.purchase.driven.customer.removed.producer.config.EnvironmentProperties;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerRemovedProducer implements CustomerRemovedRepository {

    @Value("${" + EnvironmentProperties.CUSTOMER_REMOVED_QUEUE + "}")
    private String queueName;

    private final SqsTemplate sqs;

    @Override
    public void publish(RemoveCustomerDataDTO dto) {
        sqs.send(queueName, dto);
    }
}
