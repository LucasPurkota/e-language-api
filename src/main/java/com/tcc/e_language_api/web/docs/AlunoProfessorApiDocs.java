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

public class AlunoProfessorApiDocs {
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Vincular a um professor",
            description = "Vincula um Aluno a um Professor"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vinculo criado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro"),
    })
    @RequestBody(
            description = "Dados do usuario e professor",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = PerfilDto.class),
                    examples = {
                            @ExampleObject(
                                    name = "Vincular Aluno a um professor",
                                    summary = "Cria um vinculo entre aluno e professor",
                                    description = "exemplo para criar um vinculo entre aluno e professor",
                                    value = """
                                    {
                                            "alunoId":"f97af19f-85d5-4040-874f-2d2e4226ef3b",
                                            "professorId":"aa5897da-5058-424c-b284-60e4101d700e"
                                    }
                                    """
                            )
                    }
            )
    )
    @SecurityRequirement(name = "Bearer Authentication")
    public @interface Create {}
}
