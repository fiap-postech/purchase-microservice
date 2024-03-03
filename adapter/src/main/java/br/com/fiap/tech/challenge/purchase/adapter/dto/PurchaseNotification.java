package br.com.fiap.tech.challenge.purchase.adapter.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class PurchaseNotification {

    private String template;
    private String email;
    private Map<String, Object> variables;


}
