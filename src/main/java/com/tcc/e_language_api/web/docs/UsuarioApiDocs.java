package com.tcc.e_language_api.web.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.tcc.e_language_api.web.dto.UsuarioDto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Documentação centralizada para endpoints de Usuario
 */
public class UsuarioApiDocs {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
        summary = "Criar novo usuário", 
        description = "Cria um novo usuário no sistema com os dados fornecidos"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @RequestBody(
        description = "Dados do usuário a ser criado",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = UsuarioDto.class),
            examples = {
                @ExampleObject(
                    name = "Usuário Básico",
                    summary = "Exemplo básico de usuário",
                    description = "Criação de usuário com apenas os campos obrigatórios",
                    value = """
                        {
                          "nome": "Maria Santos",
                          "cpf": "71756301077",
                          "email": "maria.santos@email.com",
                          "senha": "SenhaForte@456",
                          "enderecos": [
                            {
                              "tipo": "RESIDENCIAL",
                              "cep": "01234-567",
                              "logradouro": "Rua das Flores",
                              "bairro": "Centro",
                              "cidade": "São Paulo",
                              "uf": "SP",
                              "pais": "Brasil",
                              "complemento": "Apto 101",
                              "numero": 123
                            }
                          ]
                        }
                        """
                ),
                @ExampleObject(
                    name = "Usuário Completo",
                    summary = "Exemplo com múltiplos endereços",
                    description = "Usuário com endereço residencial e comercial",
                    value = """
                        {
                          "nome": "João Silva",
                          "cpf": "12345678901",
                          "email": "joao.silva@empresa.com",
                          "senha": "MinhaSenh@123",
                          "enderecos": [
                            {
                              "tipo": "RESIDENCIAL",
                              "cep": "12345-678",
                              "logradouro": "Avenida Principal",
                              "bairro": "Vila Nova",
                              "cidade": "Rio de Janeiro",
                              "uf": "RJ",
                              "pais": "Brasil",
                              "complemento": "Casa 15",
                              "numero": 456
                            },
                            {
                              "tipo": "COMERCIAL",
                              "cep": "87654-321",
                              "logradouro": "Rua do Comércio",
                              "bairro": "Centro",
                              "cidade": "Rio de Janeiro",
                              "uf": "RJ",
                              "pais": "Brasil",
                              "complemento": "Sala 302",
                              "numero": 789
                            }
                          ]
                        }
                        """
                )
            }
        )
    )
    public @interface CreateUsuario {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
        summary = "Atualizar usuário", 
        description = "Atualiza os dados de um usuário existente"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @RequestBody(
        description = "Dados do usuário a serem atualizados",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = UsuarioDto.class),
            examples = {
                @ExampleObject(
                    name = "Atualização Básica",
                    summary = "Atualizar dados básicos",
                    description = "Exemplo de atualização de nome e email",
                    value = """
                        {
                          "nome": "Maria Santos Silva",
                          "email": "maria.santos.silva@email.com"
                        }
                        """
                ),
                @ExampleObject(
                    name = "Atualização Completa",
                    summary = "Atualizar todos os dados",
                    description = "Exemplo de atualização completa incluindo endereços",
                    value = """
                        {
                          "nome": "Maria Santos Silva",
                          "cpf": "71756301077",
                          "email": "maria.santos.nova@email.com",
                          "senha": "NovaSenha@789",
                          "enderecos": [
                            {
                              "tipo": "RESIDENCIAL",
                              "cep": "98765-432",
                              "logradouro": "Rua Nova",
                              "bairro": "Bairro Novo",
                              "cidade": "São Paulo",
                              "uf": "SP",
                              "pais": "Brasil",
                              "complemento": "Apto 205",
                              "numero": 987
                            }
                          ]
                        }
                        """
                )
            }
        )
    )
    @SecurityRequirement(name = "Bearer Authentication")
    public @interface UpdateUsuario {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
        summary = "Deletar usuário", 
        description = "Remove um usuário do sistema permanentemente"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "403", description = "Operação não permitida")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    public @interface DeleteUsuario {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
        summary = "Buscar usuário por ID", 
        description = "Retorna os dados completos de um usuário específico"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Usuário encontrado com sucesso",
            content = @Content(
                schema = @Schema(implementation = UsuarioDto.class),
                examples = @ExampleObject(
                    name = "Usuário Encontrado",
                    summary = "Exemplo de usuário retornado",
                    description = "Dados completos de um usuário específico",
                    value = """
                        {
                          "usuarioId": "123e4567-e89b-12d3-a456-426614174000",
                          "nome": "Maria Santos",
                          "cpf": "71756301077",
                          "email": "maria.santos@email.com",
                          "enderecos": [
                            {
                              "enderecoId": "987fcdeb-51a2-43d6-b789-012345678901",
                              "tipo": "RESIDENCIAL",
                              "cep": "01234-567",
                              "logradouro": "Rua das Flores",
                              "bairro": "Centro",
                              "cidade": "São Paulo",
                              "uf": "SP",
                              "pais": "Brasil",
                              "complemento": "Apto 101",
                              "numero": 123
                            }
                          ]
                        }
                        """
                )
            )
        ),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
        @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    public @interface GetByIdUsuario {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
        summary = "Listar todos os usuários", 
        description = "Retorna uma lista com todos os usuários cadastrados no sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Lista de usuários retornada com sucesso",
            content = @Content(
                schema = @Schema(implementation = UsuarioDto[].class),
                examples = @ExampleObject(
                    name = "Lista de Usuários",
                    summary = "Exemplo de lista de usuários",
                    description = "Array com múltiplos usuários cadastrados",
                    value = """
                        [
                          {
                            "usuarioId": "123e4567-e89b-12d3-a456-426614174000",
                            "nome": "Maria Santos",
                            "cpf": "71756301077",
                            "email": "maria.santos@email.com",
                            "enderecos": [
                              {
                                "enderecoId": "987fcdeb-51a2-43d6-b789-012345678901",
                                "tipo": "RESIDENCIAL",
                                "cep": "01234-567",
                                "logradouro": "Rua das Flores",
                                "bairro": "Centro",
                                "cidade": "São Paulo",
                                "uf": "SP",
                                "pais": "Brasil",
                                "complemento": "Apto 101",
                                "numero": 123
                              }
                            ]
                          },
                          {
                            "usuarioId": "456e7890-e12b-34d5-a678-901234567890",
                            "nome": "João Silva",
                            "cpf": "12345678901",
                            "email": "joao.silva@empresa.com",
                            "enderecos": [
                              {
                                "enderecoId": "321fedcb-65a4-87d9-b012-345678901234",
                                "tipo": "COMERCIAL",
                                "cep": "87654-321",
                                "logradouro": "Rua do Comércio",
                                "bairro": "Centro",
                                "cidade": "Rio de Janeiro",
                                "uf": "RJ",
                                "pais": "Brasil",
                                "complemento": "Sala 302",
                                "numero": 789
                              }
                            ]
                          }
                        ]
                        """
                )
            )
        ),
        @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    public @interface GetAllUsuarios {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
        summary = "Obter usuário atual",
        description = "Retorna os dados do usuário logado atualmente"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Dados do usuário atual retornados com sucesso"),
        @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    public @interface GetCurrentUser {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
        summary = "Atualizar status do usuário",
        description = "Permite atualizar o status de um usuário (ATIVO, INATIVO, BLOQUEADO)"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Status atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Status inválido"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
        @ApiResponse(responseCode = "403", description = "Sem permissão para esta operação")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    public @interface UpdateUserStatus {}
}
