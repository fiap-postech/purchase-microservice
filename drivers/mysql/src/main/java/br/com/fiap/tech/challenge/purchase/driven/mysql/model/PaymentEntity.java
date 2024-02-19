package br.com.fiap.tech.challenge.purchase.driven.mysql.model;

import br.com.fiap.tech.challenge.purchase.enterprise.enums.PaymentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

@Entity
@Table(name = "payment")
@Getter
@Setter
@ToString
public class PaymentEntity extends UUIDEntity {
    @Serial
    private static final long serialVersionUID = 2772528263071091115L;

    @Id
    @Column(name = "purchase_id")
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "payment_id")
    private String paymentId;

    @NotNull
    private PaymentStatus status;

    @OneToOne
    @MapsId
    @JoinColumn(name = "purchase_id")
    private PurchaseEntity purchase;
}
