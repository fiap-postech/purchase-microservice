package br.com.fiap.tech.challenge.purchase.adapter.gateway.purchase;

import br.com.fiap.tech.challenge.purchase.adapter.dto.NotificationTemplate;
import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseNotification;
import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchaseStatusRepository;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseStatusGateway;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import static br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus.WAITING_PAYMENT;

@RequiredArgsConstructor
class PurchaseStatusGatewayImpl implements PurchaseStatusGateway {

    private final PurchaseStatusRepository repository;

    @Override
    public void publish(Purchase purchase) {
        var template = NotificationTemplate.getCurrent(purchase);

        var notification = new PurchaseNotification()
                .setTemplate(template.getTemplate())
                .setEmail(purchase.customer().email().email())
                .setVariables(buildVariables(purchase));

        repository.publish(notification);
    }

    private Map<String, Object> buildVariables(Purchase purchase) {
        var map = new HashMap<String, Object>();

        map.put("purchasecode", purchase.code());
        map.put("purchasestatus", purchase.status().getDescription());
        map.put("customername", purchase.customer().name());

        if (purchase.status() == WAITING_PAYMENT) {
            map.put("paymenturl", purchase.payment().url());
        }

        return map;
    }
}
