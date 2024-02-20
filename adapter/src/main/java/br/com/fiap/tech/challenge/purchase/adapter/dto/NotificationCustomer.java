package br.com.fiap.tech.challenge.purchase.adapter.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class NotificationCustomer {

    private String name;
    private String email;
}
