package br.com.fiap.tech.challenge.purchase.rest.resource.response;

import br.com.fiap.tech.challenge.purchase.enterprise.enums.PaymentMethod;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.PaymentStatus;
import br.com.fiap.tech.challenge.rest.common.response.Response;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class PaymentResponse extends Response {
    @Serial
    private static final long serialVersionUID = -5918722206888830745L;

    private String id;
    private LocalDate date;
    private PaymentStatus status;
    private PaymentMethod method;
    private String urlPayment;
    private BigDecimal amount;
}
