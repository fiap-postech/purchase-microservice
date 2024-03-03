package br.com.fiap.tech.challenge.purchase.launcher.service;

import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseNotification;
import br.com.fiap.tech.challenge.purchase.application.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.SimplePurchaseDTO;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class QueueService {

    private static final Logger LOGGER = getLogger(QueueService.class);

    public void received(PurchaseDTO dto) {
        LOGGER.info(String.format("%s", dto));
    }

    public void received(SimplePurchaseDTO dto) {
        LOGGER.info(String.format("%s", dto));
    }

    public void received(PurchaseNotification dto) {
        LOGGER.info(String.format("%s", dto));
    }

}
