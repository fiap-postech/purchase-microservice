package br.com.fiap.tech.challenge.purchase.adapter.dto;

import br.com.fiap.tech.challenge.purchase.enterprise.enums.ProductCategory;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.DEDUCTION;


@Getter
@Setter
@Accessors(chain = true)
@JsonTypeInfo(use = DEDUCTION, defaultImpl = SingleProductInputDTO.class)
@JsonSubTypes({ @Type(SingleProductInputDTO.class),  @Type(ComboProductInputDTO.class) })
public class SingleProductInputDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1464909268054662495L;

    private String id;
    private String name;
    private String description;
    private String image;
    private BigDecimal price;
    private BigDecimal fullPrice;
    private BigDecimal discount;
    private Boolean enabled;
    private ProductCategory category;
}
