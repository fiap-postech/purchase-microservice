package br.com.fiap.tech.challenge.purchase.rest.resource.response;

import br.com.fiap.tech.challenge.rest.common.response.Response;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Representação de um Produto")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProductResponse.class, name = "single"),
        @JsonSubTypes.Type(value = ComboResponse.class, name = "combo")
})
public class ProductResponse extends Response {
    @Serial
    private static final long serialVersionUID = 1464909268054662495L;

    @Schema(description = "Identificador do produto", example = "2a87c90b-cecf-4d40-89aa-a83d1b093c0e")
    private String id;

    @Schema(description = "Nome do produto", example = "hamburguer de carne")
    private String name;

    @Schema(description = "Descrição do produto", example = "Dois hambúrgueres (100% carne bovina), alface americana, queijo sabor cheddar, molho especial, cebola, picles e pão com gergelim")
    private String description;
}
