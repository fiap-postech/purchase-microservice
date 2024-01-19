package br.com.fiap.tech.challenge.purchase.application.dto;

import br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class PurchaseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -8440508890936918851L;

    private String id;
    private CustomerDTO customer;
    private PurchaseStatus status;
    private LocalDate date;
    private List<PurchaseItemDTO> items;
    private PaymentDTO payment;
}
