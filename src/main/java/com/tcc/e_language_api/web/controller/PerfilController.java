package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.entity.Perfil;
import com.tcc.e_language_api.jwt.JwtUserDetails;
import com.tcc.e_language_api.service.PerfilService;
import com.tcc.e_language_api.web.docs.PerfilApiDocs;
import com.tcc.e_language_api.web.dto.PerfilDto;

import com.tcc.e_language_api.web.dto.mapper.PerfilMapper;
import com.tcc.e_language_api.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<?> createPerfil(@RequestBody PerfilDto dto, HttpServletRequest request) {
        try {
            perfilService.create(dto);
            String tipoPerfil = dto.getTipoPerfilId() == 1 ? "ALUNO" : 
                               dto.getTipoPerfilId() == 2 ? "PROFESSOR" : "ADMIN";
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ErrorMessage(request, HttpStatus.OK,"Perfil " + tipoPerfil + " criado com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id, HttpServletRequest request) {
        try {
            perfilService.delete(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ErrorMessage(request, HttpStatus.OK,"Perfil deletado com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id, HttpServletRequest request) {
        try {
            Perfil perfil = perfilService.getById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(PerfilMapper.toResponse(perfil));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()));
        }
    }

    @GetMapping("/{tipoPerfilId}/tipo-perfil")
    public ResponseEntity<?> getByTipoPerfil(@PathVariable int tipoPerfilId, @RequestParam(required = false) UUID idiomaId, HttpServletRequest request) {
        try {
            List<Perfil> perfil = perfilService.getByTipoPerfil(tipoPerfilId, idiomaId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(PerfilMapper.toResponseList(perfil));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()));
        }
    }

    @GetMapping("/usuario/{tipoPerfil}/tipo-perfil")
    public ResponseEntity<?> getByUser(@PathVariable String tipoPerfil, @AuthenticationPrincipal JwtUserDetails userDetails, HttpServletRequest request) {
        try {
            Perfil perfil = perfilService.getByUsuarioAndTipoPerfil(tipoPerfil, userDetails.getUsername());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(PerfilMapper.toResponse(perfil));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()));
        }
    }
}
