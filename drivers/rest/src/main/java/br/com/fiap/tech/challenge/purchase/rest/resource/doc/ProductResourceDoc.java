package br.com.fiap.tech.challenge.purchase.rest.resource.doc;

import br.com.fiap.tech.challenge.purchase.enterprise.enums.ProductCategory;
import br.com.fiap.tech.challenge.rest.common.handler.error.ApiErrorResponse;
import br.com.fiap.tech.challenge.purchase.rest.resource.request.CreateProductRequest;
import br.com.fiap.tech.challenge.purchase.rest.resource.request.UpdateProductRequest;
import br.com.fiap.tech.challenge.purchase.rest.resource.response.ProductResponse;
import br.com.fiap.tech.challenge.purchase.application.util.ResponseList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
@Tag(name = "Produto", description = "API responsável pelo gerenciamento de Produto")
public interface ProductResourceDoc {

    @Operation(
        summary = "Retorna todos os produtos",
        description = "Busca todos os produtos cadastrados no banco de dados de acordo com a requisição",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK - Retorno em caso de sucesso todos os produtos de acordo com a requisição", useReturnTypeSchema = true)
    })
    ResponseList<ProductResponse> getAllAvailable(Pageable pageable,
                                                  @Parameter(description = "Filtra pela categoria do produto", required = true) ProductCategory category);
    @Operation(
            summary = "Retorna um produto pelo UUID",
            description = "Busca o produto registrado no banco de dados daquele UUID",
            responses = {
                @ApiResponse(responseCode = "200", description = "OK - Retorno em caso de sucesso um produto de acordo com a requisição", content = { @Content(schema = @Schema(implementation = ProductResponse.class), mediaType = "application/json") }),
                @ApiResponse(responseCode = "400", description = "O UUID de produto fornecido não foi encontrado", content = { @Content(schema = @Schema()) })
            }
    )
    ProductResponse getByUUID(@Parameter(description = "UUID de produto a ser pesquisado", required = true) String uuid);
    @Operation(
            summary = "Cadastra um novo produto",
            description = "Cadastra um novo produto no banco de dados.",
            responses = {
                @ApiResponse(responseCode = "201", description = "Retorno em caso de sucesso se um produto foi cadastrado", content = { @Content(schema = @Schema(implementation = ProductResponse.class), mediaType = "application/json") }),
                @ApiResponse(responseCode = "400", description = "Retorno informando qual campo do produto está incorreto e por qual motivo", content = { @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = "application/json") })
            }
    )
    ProductResponse create(CreateProductRequest request);
    @Operation(
            summary = "Atualiza um produto",
            description = "Atualiza os dados do produto no banco de dados",
            responses = {
                @ApiResponse(responseCode = "200", description = "OK - Retorno em caso de sucesso em que o produto foi atualizado", content = { @Content(schema = @Schema(implementation = ProductResponse.class), mediaType = "application/json") }),
                @ApiResponse(responseCode = "400", description = "Retorno informando qual campo do produto está incorreto e por qual motivo", content = { @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = "application/json") })
            }
    )
    ProductResponse update(UpdateProductRequest request);
    @Operation(
            summary = "Habilita um produto",
            description = "Habilita um produto com status desabilitado no banco de dados.",
            responses = {
                @ApiResponse(responseCode = "200", description = "OK - Retorno em caso de sucesso em que produto foi habilitado", content = { @Content(schema = @Schema(implementation = ProductResponse.class), mediaType = "application/json") })
            }
    )
    ProductResponse enable(@Parameter(description = "UUID do produto a ser habilitado", required = true) String uuid);
    @Operation(
            summary = "Desabilita um produto",
            description = "Desabilita um produto com status habilitado no banco de dados.",
            responses = {
                @ApiResponse(responseCode = "200", description = "OK - Retorno em caso de sucesso se um produto foi desabilitado", content = { @Content(schema = @Schema(implementation = ProductResponse.class), mediaType = "application/json") })
            }
    )
    ProductResponse disable(@Parameter(description = "UUID do produto a ser desabilitado", required = true) String uuid);
}
