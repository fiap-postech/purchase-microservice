package br.com.fiap.tech.challenge.purchase.driven.purchase.created.producer.messaging;

import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchaseCreatedRepository;
import br.com.fiap.tech.challenge.purchase.application.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.purchase.driven.purchase.created.producer.config.EnvironmentProperties;
import br.com.fiap.tech.challenge.purchase.driven.purchase.created.producer.mapping.PurchaseOutputDTOMapper;
import io.awspring.cloud.sns.core.SnsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseCreatedProducer implements PurchaseCreatedRepository {

    @Value("${" + EnvironmentProperties.PURCHASE_CREATED_TOPIC + "}")
    private String topicName;

    private final SnsTemplate sns;
    private final PurchaseOutputDTOMapper mapper;

    @Override
    public void notify(PurchaseDTO dto) {
        sns.convertAndSend(topicName, mapper.toOutputDTO(dto));
    }
}
