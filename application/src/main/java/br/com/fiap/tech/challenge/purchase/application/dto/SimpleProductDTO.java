package br.com.fiap.tech.challenge.purchase.application.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;


@Data
@Accessors(chain = true)
public class SimpleProductDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1464909268054662495L;

    private String id;
    private String name;
    private String description;
}
