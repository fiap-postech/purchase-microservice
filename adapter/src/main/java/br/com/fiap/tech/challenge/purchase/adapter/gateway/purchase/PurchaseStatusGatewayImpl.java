package br.com.fiap.tech.challenge.purchase.adapter.gateway.purchase;

import br.com.fiap.tech.challenge.purchase.adapter.dto.NotificationCustomer;
import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseNotification;
import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchaseStatusRepository;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseStatusGateway;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class PurchaseStatusGatewayImpl implements PurchaseStatusGateway {

    private final PurchaseStatusRepository repository;

    @Override
    public void publish(Purchase purchase) {
        var customer = new NotificationCustomer()
                .setName(purchase.customer().name())
                .setEmail(purchase.customer().email().email());

        var notification = new PurchaseNotification()
                .setCode(purchase.code())
                .setStatus(purchase.status().getDescription())
                .setCustomer(customer);

        repository.publish(notification);
    }
}
