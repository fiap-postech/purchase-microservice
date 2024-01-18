package br.com.fiap.tech.challenge.purchase.driven.mysql.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

@Entity
@Table(name = "combo")
@Getter
@Setter
@ToString
public class ComboEntity extends ProductEntity {
    @Serial
    private static final long serialVersionUID = 2767642911036287466L;

    @ManyToOne
    @JoinColumn(name = "beverage_id")
    private ProductEntity beverage;

    @ManyToOne
    @JoinColumn(name = "sandwich_id")
    private ProductEntity sandwich;

    @ManyToOne
    @JoinColumn(name = "side_dish_id")
    private ProductEntity sideDish;
}
