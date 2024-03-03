package br.com.fiap.tech.challenge.purchase.driven.purchase.paid.producer.messaging;

import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchasePaidRepository;
import br.com.fiap.tech.challenge.purchase.application.dto.SimplePurchaseDTO;
import io.awspring.cloud.sns.core.SnsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static br.com.fiap.tech.challenge.purchase.driven.purchase.paid.producer.config.EnvironmentProperties.PURCHASE_PAID_TOPIC;

@Service
@RequiredArgsConstructor
public class PurchasePaidProducer implements PurchasePaidRepository {

    @Value("${" + PURCHASE_PAID_TOPIC + "}")
    private String topicName;

    private final SnsTemplate sns;

    @Override
    public void notify(SimplePurchaseDTO dto) {
        sns.convertAndSend(topicName, dto);
    }
}
