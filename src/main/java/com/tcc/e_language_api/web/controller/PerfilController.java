package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.service.PerfilService;
import com.tcc.e_language_api.web.docs.PerfilApiDocs;
import com.tcc.e_language_api.web.dto.PerfilDto;

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
    @PerfilApiDocs.CreatePerfil
    public ResponseEntity<String> createPerfil(@org.springframework.web.bind.annotation.RequestBody PerfilDto dto) {
        try {
            perfilService.create(dto);
            String tipoPerfil = dto.getTipoPerfilId() == 1 ? "ALUNO" : 
                               dto.getTipoPerfilId() == 2 ? "PROFESSOR" : "ADMIN";
            return ResponseEntity.status(HttpStatus.CREATED)
                .body("Perfil " + tipoPerfil + " criado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
