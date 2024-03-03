package br.com.fiap.tech.challenge.purchase.adapter.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serial;

@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ComboProductInputDTO extends SingleProductInputDTO {
    @Serial
    private static final long serialVersionUID = 7002042207857288411L;

    private SingleProductInputDTO beverage;
    private SingleProductInputDTO sandwich;
    private SingleProductInputDTO sideDish;
}
