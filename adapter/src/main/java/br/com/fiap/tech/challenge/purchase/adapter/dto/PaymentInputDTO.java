package br.com.fiap.tech.challenge.purchase.adapter.dto;

import br.com.fiap.tech.challenge.purchase.enterprise.enums.PaymentMethod;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.PaymentStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class PaymentInputDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 312532141453893566L;

    private String id;
    private LocalDate date;
    private PaymentStatus status;
    private PaymentMethod method;
    private String urlPayment;
    private BigDecimal amount;
}
