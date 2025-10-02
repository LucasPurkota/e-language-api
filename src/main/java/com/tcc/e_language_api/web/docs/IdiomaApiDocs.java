package com.tcc.e_language_api.web.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.tcc.e_language_api.web.dto.IdiomaDto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Documentação centralizada para endpoints de Idioma
 */
public class IdiomaApiDocs {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
        summary = "Listar todos os idiomas", 
        description = "Retorna uma lista com todos os idiomas disponíveis no sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Lista de idiomas retornada com sucesso",
            content = @Content(schema = @Schema(implementation = IdiomaDto.class))
        )
    })
    public @interface GetAll {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
        summary = "Criar novo idioma", 
        description = "Cria um novo idioma no sistema (apenas para admins)"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Idioma criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
        @ApiResponse(responseCode = "403", description = "Usuário não tem permissão para esta operação")
    })
    @RequestBody(
        description = "Dados do idioma a ser criado",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = IdiomaDto.class),
            examples = {
                @ExampleObject(
                    name = "Idioma Inglês",
                    summary = "Criar idioma Inglês",
                    description = "Exemplo para criar o idioma Inglês",
                    value = """
                        {
                          "nome": "Inglês",
                          "sigla": "EN"
                        }
                        """
                ),
                @ExampleObject(
                    name = "Idioma Espanhol", 
                    summary = "Criar idioma Espanhol",
                    description = "Exemplo para criar o idioma Espanhol",
                    value = """
                        {
                          "nome": "Espanhol",
                          "sigla": "ES"
                        }
                        """
                )
            }
        )
    )
    @SecurityRequirement(name = "Bearer Authentication")
    public @interface CreateIdioma {}
}