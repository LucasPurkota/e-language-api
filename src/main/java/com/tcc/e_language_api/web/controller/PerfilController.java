package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.repository.PerfilRepository;
import com.tcc.e_language_api.service.PerfilService;
import com.tcc.e_language_api.web.dto.PerfilDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/perfil")
@Tag(name = "3. Perfis", description = "Operações relacionadas ao gerenciamento de perfis de usuários")
public class PerfilController {
    private final PerfilService perfilService;

    @PostMapping
    @Operation(summary = "Criar perfil para usuário", 
               description = "Atribui um perfil (ALUNO ou PROFESSOR) a um usuário específico")
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
                )
            }
        )
    )
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<String> createPerfil(@org.springframework.web.bind.annotation.RequestBody PerfilDto dto) {
        try {
            perfilService.create(dto);
            String tipoPerfil = dto.getTipoPerfilId() == 1 ? "ALUNO" : "PROFESSOR";
            return ResponseEntity.status(HttpStatus.CREATED)
                .body("Perfil " + tipoPerfil + " criado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
