package br.com.fiap.tech.challenge.purchase.application.dto;

import br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class SimplePurchaseDTO {

    private String id;
    private String code;
    private SimpleCustomerDTO customer;
    private PurchaseStatus status;
    private LocalDate date;
    private List<SimplePurchaseItemDTO> items;

}
