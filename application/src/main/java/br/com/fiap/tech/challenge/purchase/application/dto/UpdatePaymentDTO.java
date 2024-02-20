package br.com.fiap.tech.challenge.purchase.application.dto;

import br.com.fiap.tech.challenge.purchase.enterprise.enums.PaymentStatus;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UpdatePaymentDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 312532141453893566L;

    private String id;
    private String purchaseId;
    private PaymentStatus status;
}
