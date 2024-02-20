package br.com.fiap.tech.challenge.purchase.driven.purchase.status.producer.messaging;

import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseNotification;
import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchaseStatusRepository;
import br.com.fiap.tech.challenge.purchase.driven.purchase.status.producer.config.EnvironmentProperties;
import io.awspring.cloud.sns.core.SnsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseNotificationProducer implements PurchaseStatusRepository {

    @Value("${" + EnvironmentProperties.PURCHASE_NOTIFICATION_TOPIC + "}")
    private String topicName;

    private final SnsTemplate sns;

    @Override
    public void publish(PurchaseNotification notification) {
        sns.convertAndSend(topicName, notification);
    }
}
