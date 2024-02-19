package br.com.fiap.tech.challenge.purchase.adapter.dto;

import br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Accessors(chain = true)
public class PurchaseInputDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -8440508890936918851L;

    private String id;
    private CustomerInputDTO customer;
    private PurchaseStatus status = PurchaseStatus.CREATED;
    private LocalDate date = LocalDate.now();
    private List<PurchaseItemInputDTO> items;
}
