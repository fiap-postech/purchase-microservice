package br.com.fiap.tech.challenge.purchase.application.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ComboDTO extends FullProductDTO {
    @Serial
    private static final long serialVersionUID = 7002042207857288411L;

    private FullProductDTO beverage;
    private FullProductDTO sandwich;
    private FullProductDTO sideDish;
}
