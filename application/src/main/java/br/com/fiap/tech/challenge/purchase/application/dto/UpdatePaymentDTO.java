package br.com.fiap.tech.challenge.purchase.application.dto;

import br.com.fiap.tech.challenge.purchase.enterprise.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePaymentDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 312532141453893566L;

    private String id;
    private String purchaseId;
    private PaymentStatus status;
}
