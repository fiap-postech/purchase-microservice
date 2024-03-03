package br.com.fiap.tech.challenge.purchase.application.dto;

import br.com.fiap.tech.challenge.purchase.enterprise.enums.DataRemovalStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Data
@Accessors(chain = true)
public class RemoveCustomerDataDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 8349920253193450259L;

    private String id;
    private String application;
    private DataRemovalStatus status;
}
