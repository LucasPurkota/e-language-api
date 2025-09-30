package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.entity.Idioma;
import com.tcc.e_language_api.jwt.JwtUserDetails;
import com.tcc.e_language_api.service.IdiomaService;
import com.tcc.e_language_api.web.dto.IdiomaDto;
import com.tcc.e_language_api.web.dto.mapper.IdiomaMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/idioma")
@Tag(name = "4. Idiomas", description = "Operações relacionadas ao gerenciamento de idiomas")
public class IdiomaController {
    private final IdiomaService idiomaService;
    
    @PostMapping
    @Operation(summary = "Criar novo idioma", 
               description = "Cria um novo idioma no sistema (apenas para admins)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Idioma criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
        @ApiResponse(responseCode = "403", description = "Usuário não tem permissão para esta operação")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<String> create(@RequestBody @Valid IdiomaDto idiomaDto, @AuthenticationPrincipal JwtUserDetails userDetails) {
        idiomaService.create(IdiomaMapper.toEntity(idiomaDto), userDetails.getRole());
        return ResponseEntity.status(HttpStatus.CREATED).body("Idioma criado com sucesso!");
    }

    @GetMapping
    @Operation(summary = "Listar todos os idiomas", 
               description = "Retorna uma lista com todos os idiomas disponíveis no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de idiomas retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = IdiomaDto.class)))
    })
    public ResponseEntity<List<IdiomaDto>> getAll() {
        List<Idioma> idiomas = idiomaService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(IdiomaMapper.toListDto(idiomas));
    }
}
