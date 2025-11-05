package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.entity.Perfil;
import com.tcc.e_language_api.service.PerfilService;
import com.tcc.e_language_api.web.docs.PerfilApiDocs;
import com.tcc.e_language_api.web.dto.PerfilDto;

import com.tcc.e_language_api.web.dto.mapper.PerfilMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/perfil")
@Tag(name = "3. Perfis", description = "Operações relacionadas ao gerenciamento de perfis de usuários")
public class PerfilController {
    private final PerfilService perfilService;

    @PostMapping
    @PerfilApiDocs.CreatePerfil
    public ResponseEntity<String> createPerfil(@RequestBody PerfilDto dto) {
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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        try {
            perfilService.delete(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Perfil deletado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        try {
            Perfil perfil = perfilService.getById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(PerfilMapper.toResponse(perfil));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{tipoPerfilId}/tipo-perfil")
    public ResponseEntity<?> getById(@PathVariable int tipoPerfilId) {
        try {
            List<Perfil> perfil = perfilService.getByTipoPerfil(tipoPerfilId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(PerfilMapper.toResponseList(perfil));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
