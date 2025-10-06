package com.tcc.e_language_api.web.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.tcc.e_language_api.web.dto.UnidadeDto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Documentação centralizada para endpoints de Unidade
 */
public class UnidadeApiDocs {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
        summary = "Buscar unidade por ID", 
        description = "Retorna os detalhes de uma unidade específica pelo seu identificador"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Unidade encontrada com sucesso",
            content = @Content(
                schema = @Schema(implementation = UnidadeDto.class),
                examples = @ExampleObject(
                    name = "Unidade Encontrada",
                    summary = "Exemplo de unidade retornada",
                    description = "Dados completos de uma unidade específica",
                    value = """
                        {
                          "unidadeId": "8c6be421-cdcd-427c-887a-cd5519990762",
                          "idiomaId": "4323b23e-6a60-4ad4-9c25-27ac8720caff",
                          "nivelIdiomaId": 1,
                          "numero": 5,
                          "titulo": "How are you",
                          "conteudo": "verbos....."
                        }
                        """
                )
            )
        ),
        @ApiResponse(responseCode = "404", description = "Unidade não encontrada"),
        @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    public @interface GetById {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
        summary = "Buscar unidades por idioma", 
        description = "Retorna todas as unidades de um idioma específico"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Lista de unidades do idioma retornada com sucesso",
            content = @Content(
                schema = @Schema(implementation = UnidadeDto[].class),
                examples = @ExampleObject(
                    name = "Lista de Unidades",
                    summary = "Exemplo de resposta com unidades",
                    description = "Lista com as unidades encontradas para o idioma",
                    value = """
                        [
                          {
                            "unidadeId": "asdfasd-asdfas-asdf-asdfasdfadf",
                            "idiomaId": "4323b23e-6a60-4ad4-9c25-27ac8720caff",
                            "nivelIdiomaId": 1,
                            "numero": 5,
                            "titulo": "How are you",
                            "conteudo": "verbos....."
                          },
                          {
                            "unidadeId": "123e4567-e89b-12d3-a456-426614174000",
                            "idiomaId": "4323b23e-6a60-4ad4-9c25-27ac8720caff",
                            "nivelIdiomaId": 1,
                            "numero": 6,
                            "titulo": "What's your name",
                            "conteudo": "pronomes pessoais....."
                          }
                        ]
                        """
                )
            )
        ),
        @ApiResponse(responseCode = "404", description = "Idioma não encontrado"),
        @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    public @interface GetByIdiomaId {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
        summary = "Listar todas as unidades", 
        description = "Retorna uma lista com todas as unidades do sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Lista de unidades retornada com sucesso",
            content = @Content(schema = @Schema(implementation = UnidadeDto.class))
        ),
        @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    public @interface GetAll {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
        summary = "Criar nova unidade", 
        description = "Cria uma nova unidade de ensino no sistema (apenas para admins)"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Unidade criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
        @ApiResponse(responseCode = "403", description = "Usuário não tem permissão para esta operação")
    })
    @RequestBody(
        description = "Dados da unidade a ser criada",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = UnidadeDto.class),
            examples = {
                @ExampleObject(
                    name = "Unidade Básica",
                    summary = "Exemplo de unidade básica",
                    description = "Exemplo para criar uma unidade de nível básico",
                    value = """
                        {
                          "nome": "Unidade 1 - Apresentações",
                          "descricao": "Aprenda a se apresentar e cumprimentar em inglês",
                          "nivelDificuldade": "BASICO",
                          "ordem": 1,
                          "idiomaId": "550e8400-e29b-41d4-a716-446655440000"
                        }
                        """
                ),
                @ExampleObject(
                    name = "Unidade Intermediária",
                    summary = "Exemplo de unidade intermediária",
                    description = "Exemplo para criar uma unidade de nível intermediário",
                    value = """
                        {
                          "nome": "Unidade 5 - Conversas Profissionais",
                          "descricao": "Desenvolva habilidades para conversas no ambiente de trabalho",
                          "nivelDificuldade": "INTERMEDIARIO",
                          "ordem": 5,
                          "idiomaId": "550e8400-e29b-41d4-a716-446655440000"
                        }
                        """
                )
            }
        )
    )
    @SecurityRequirement(name = "Bearer Authentication")
    public @interface CreateUnidade {}
}