package br.com.fiap.tech.challenge.purchase.rest.resource.response;

import br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus;
import br.com.fiap.tech.challenge.rest.common.response.Response;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Schema(description = "Representação de um Pedido")
public class PurchaseResponse extends Response {
    @Serial
    private static final long serialVersionUID = -477119349237609062L;

    @Schema(description = "Identificador do pedido", example = "a9ce357e-4805-412b-9563-98fdb2777ba6")
    private String id;
    private CustomerResponse customer;
    private PurchaseStatus status;
    private LocalDate date;
    private List<PurchaseItemResponse> items;
    private PaymentResponse payment;
}
