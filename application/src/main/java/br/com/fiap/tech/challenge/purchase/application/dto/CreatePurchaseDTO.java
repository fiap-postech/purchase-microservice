package br.com.fiap.tech.challenge.purchase.application.dto;

import br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class CreatePurchaseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -1383025569820101900L;

    private FullCustomerDTO customer;
    private PurchaseStatus status;
    private LocalDate date;
    private List<FullPurchaseItemDTO> items;
    private PaymentDTO payment;
}
