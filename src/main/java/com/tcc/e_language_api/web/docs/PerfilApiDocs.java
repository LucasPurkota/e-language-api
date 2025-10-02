package com.tcc.e_language_api.web.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.tcc.e_language_api.web.dto.PerfilDto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Documentação centralizada para endpoints de Perfil
 */
public class PerfilApiDocs {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
        summary = "Criar perfil para usuário", 
        description = "Atribui um perfil (ALUNO, PROFESSOR ou ADMIN) a um usuário específico"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Perfil criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos ou usuário já possui este perfil"),
        @ApiResponse(responseCode = "404", description = "Usuário ou tipo de perfil não encontrado")
    })
    @RequestBody(
        description = "Dados do perfil a ser criado",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = PerfilDto.class),
            examples = {
                @ExampleObject(
                    name = "Perfil Aluno",
                    summary = "Atribuir perfil de ALUNO",
                    description = "Exemplo para criar perfil de aluno para um usuário",
                    value = """
                        {
                          "usuarioId": "31f7765da-d094-490c-b22f-04a5fc38a9a1",
                          "tipoPerfilId": 1
                        }
                        """
                ),
                @ExampleObject(
                    name = "Perfil Professor",
                    summary = "Atribuir perfil de PROFESSOR",
                    description = "Exemplo para criar perfil de professor para um usuário",
                    value = """
                        {
                          "usuarioId": "31f7765da-d094-490c-b22f-04a5fc38a9a1",
                          "tipoPerfilId": 2
                        }
                        """
                ),
                @ExampleObject(
                    name = "Perfil Admin",
                    summary = "Atribuir perfil de ADMIN",
                    description = "Exemplo para criar perfil de administrador para um usuário",
                    value = """
                        {
                          "usuarioId": "31f7765da-d094-490c-b22f-04a5fc38a9a1",
                          "tipoPerfilId": 3
                        }
                        """
                )
            }
        )
    )
    @SecurityRequirement(name = "Bearer Authentication")
    public @interface CreatePerfil {}
}