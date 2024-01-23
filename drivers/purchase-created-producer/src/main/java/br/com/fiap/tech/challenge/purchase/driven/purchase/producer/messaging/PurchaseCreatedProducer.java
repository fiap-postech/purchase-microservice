package br.com.fiap.tech.challenge.purchase.driven.purchase.producer.messaging;

import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchaseCreatedRepository;
import br.com.fiap.tech.challenge.purchase.application.dto.PurchaseDTO;
import io.awspring.cloud.sns.core.SnsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static br.com.fiap.tech.challenge.purchase.driven.purchase.producer.config.EnvironmentProperties.PURCHASE_CREATED_TOPIC;

@Service
@RequiredArgsConstructor
public class PurchaseCreatedProducer implements PurchaseCreatedRepository {

    @Value("${" + PURCHASE_CREATED_TOPIC + "}")
    private String topicName;

    private final SnsTemplate sns;

    @Override
    public void notify(PurchaseDTO dto) {
        sns.send(topicName, MessageBuilder.withPayload(dto).build());
    }
}
