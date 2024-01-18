package br.com.fiap.tech.challenge.purchase.driven.mysql.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class PurchaseItemEntityId implements Serializable {

    @Serial
    private static final long serialVersionUID = -6903805050356181525L;

    private Long product;
    private Long purchase;
}
