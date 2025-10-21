package com.tcc.e_language_api.web.docs;

import com.tcc.e_language_api.web.dto.PerfilDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class AulaApiDocs {
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Criar Aula",
            description = "Cria uma aula para uma unidade"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aula criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro referente a salvamento de banco"),
            @ApiResponse(responseCode = "500", description = "Erro interno de servidor"),
    })
    @RequestBody(
            description = "Dados para criar uma aula",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = PerfilDto.class),
                    examples = {
                            @ExampleObject(
                                    name = "Criar uma aula",
                                    summary = "Cria uma aula para uma unidade",
                                    description = "exemplo para criar uma aula",
                                    value = """
                                    {
                                            "unidadeId":"fd1c9782-5306-4aff-9732-2f4f1ba85ef6",
                                            "numero":4,
                                            "titulo":"testeste56",
                                            "conteudo":"testestesarhbadrgawer",
                                            "linkVideo":"https://www.youtube.com/4"
                                    }
                                    """
                            )
                    }
            )
    )
    @SecurityRequirement(name = "Bearer Authentication")
    public @interface Create {}
}
