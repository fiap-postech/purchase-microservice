package br.com.fiap.tech.challenge.purchase.adapter.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PurchaseNotification {

    private String code;
    private String status;
    private NotificationCustomer customer;

}
