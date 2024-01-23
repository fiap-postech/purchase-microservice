package br.com.fiap.tech.challenge.purchase.adapter.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Data
@Accessors(chain = true)
public class CustomerInputDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -447066642189592390L;

    private String id;
    private String name;
    private String email;
    private String document;
    private boolean enabled;
}
