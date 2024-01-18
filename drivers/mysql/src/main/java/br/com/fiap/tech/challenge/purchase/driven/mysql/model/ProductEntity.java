package br.com.fiap.tech.challenge.purchase.driven.mysql.model;

import br.com.fiap.tech.challenge.purchase.enterprise.enums.ProductCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@ToString
public class ProductEntity extends JPAEntity {
    @Serial
    private static final long serialVersionUID = -3103908935115324117L;

    @NotBlank
    private String name;

    @NotBlank
    @Column(columnDefinition = "text")
    private String description;

    @NotNull
    @PositiveOrZero
    private BigDecimal price;

    @NotBlank
    @Column(columnDefinition = "text")
    private String image;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    private boolean enabled;
}
