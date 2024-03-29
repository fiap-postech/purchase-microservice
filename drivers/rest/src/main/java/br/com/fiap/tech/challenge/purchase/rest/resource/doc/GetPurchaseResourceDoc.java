package br.com.fiap.tech.challenge.purchase.rest.resource.doc;

import br.com.fiap.tech.challenge.purchase.rest.resource.response.PurchaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Pedido", description = "API responsável pelo gerenciamento de Pedido")
public interface GetPurchaseResourceDoc {

    @Operation(
            summary = "Retorna um pedido pelo UUID",
            description = "Busca o pedido registrado no banco de dados pelo UUID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK - Retorno em caso de sucesso um pedido de acordo com a requisição", content = { @Content(schema = @Schema(implementation = PurchaseResponse.class), mediaType = "application/json") }),
                    @ApiResponse(responseCode = "400", description = "O UUID do pedido fornecido não foi encontrado", content = { @Content(schema = @Schema()) })
            }
    )
    PurchaseResponse getByUUID(@Parameter(description = "UUID do pedido a ser pesquisado", required = true) String uuid);
}
