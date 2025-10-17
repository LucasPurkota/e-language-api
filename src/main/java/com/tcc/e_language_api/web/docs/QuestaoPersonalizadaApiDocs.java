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

public class QuestaoPersonalizadaApiDocs {
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Cria Questao Personalizada",
            description = "Cria uma questao personalizada para um aluno"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Questao criada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro"),
    })
    @RequestBody(
            description = "Dados da questao a ser criada",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = PerfilDto.class),
                    examples = {
                            @ExampleObject(
                                    name = "Questao Personalizada",
                                    summary = "Cria uma questao personalizada",
                                    description = "exemplo para criar uma questao personalizada",
                                    value = """
                                    {
                                        "idiomaId":"59a6fcc5-85ee-497a-9f0d-52e69ea02fcb",
                                        "alunoProfessorId":"dfbcf3b4-0c33-4f44-9d0d-c6da9aa46915",
                                        "nivelDificuldadeId":1,
                                        "enunciado":"Complete....",
                                        "tipoQuestaoId":1,
                                        "gabarito":"A"
                                    }
                                    """
                            )
                    }
            )
    )
    @SecurityRequirement(name = "Bearer Authentication")
    public @interface Create {}
}
