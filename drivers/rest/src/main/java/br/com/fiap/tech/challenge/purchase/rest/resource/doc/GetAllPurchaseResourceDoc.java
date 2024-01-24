package br.com.fiap.tech.challenge.purchase.rest.resource.doc;

import br.com.fiap.tech.challenge.purchase.application.util.ResponseList;
import br.com.fiap.tech.challenge.purchase.rest.resource.response.PurchaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;

@Tag(name = "Pedido", description = "API responsável pelo gerenciamento de Pedido")
public interface GetAllPurchaseResourceDoc {

    @Operation(
            summary = "Retorna todos os pedidos",
            description = "Busca todos os pedidos cadastrados no banco de dados de acordo com a requisição",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK - Retorno em caso de sucesso todos os pedidos de acordo com a requisição", useReturnTypeSchema = true)
            }
    )
    ResponseList<PurchaseResponse> getAllAvailable(Pageable pageable);

}
