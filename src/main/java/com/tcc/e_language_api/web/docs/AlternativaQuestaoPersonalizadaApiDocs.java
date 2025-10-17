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

public class AlternativaQuestaoPersonalizadaApiDocs {
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Cria Alternativa Questao Personalizada",
            description = "Cria uma alternativa para uma questao personalizada"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Alternativa criada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro"),
    })
    @RequestBody(
            description = "Dados da alternativa a ser criada",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = PerfilDto.class),
                    examples = {
                            @ExampleObject(
                                    name = "Alternativa Questao Personalizada",
                                    summary = "Cria uma Alternativa Questao Personalizada",
                                    description = "exemplo para criar uma Alternativa Questao Personalizada",
                                    value = """
                                    {
                                            "questaoPersonalizadaId":"fc9e69d1-6df4-4b05-b266-b25789934778",
                                            "alternativa":"A",
                                            "descricao":"teste"
                                    }
                                    """
                            )
                    }
            )
    )
    @SecurityRequirement(name = "Bearer Authentication")
    public @interface Create {}
}
