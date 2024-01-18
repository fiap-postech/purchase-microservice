package br.com.fiap.tech.challenge.purchase.driven.mysql.model;

import br.com.fiap.tech.challenge.purchase.enterprise.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payment")
@Getter
@Setter
@ToString
public class PaymentEntity extends JPAEntity {
    @Serial
    private static final long serialVersionUID = 2772528263071091115L;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private PurchaseEntity purchase;

    @NotBlank
    private String method;

    @NotNull
    private LocalDate date;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @NotNull
    @Positive
    private BigDecimal amount;
}
