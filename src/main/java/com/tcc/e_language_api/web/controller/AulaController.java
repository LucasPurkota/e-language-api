package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.entity.Aula;
import com.tcc.e_language_api.jwt.JwtUserDetails;
import com.tcc.e_language_api.service.AulaService;
import com.tcc.e_language_api.web.docs.AulaApiDocs;
import com.tcc.e_language_api.web.dto.AulaDto;
import com.tcc.e_language_api.web.dto.mapper.AulaMapper;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/aula")
@Tag(name = "Aula", description = "Operações relacionadas ao gerenciamento de uma aula")
public class AulaController {

    private final AulaService aulaService;

    @PostMapping
    @AulaApiDocs.Create
    public ResponseEntity<?> create(@RequestBody AulaDto aulaDTO, @AuthenticationPrincipal JwtUserDetails userDetails, HttpServletRequest request) {
        try {
            aulaService.create(AulaMapper.toEntity(aulaDTO), userDetails.getRole());
            return ResponseEntity.status(HttpStatus.CREATED).body(new ErrorMessage(request, HttpStatus.CREATED, "Unidade criada com sucesso!"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody AulaDto dto, @AuthenticationPrincipal JwtUserDetails userDetails, HttpServletRequest request) {
        try {
            aulaService.update(id, dto, userDetails.getRole());
            return ResponseEntity.status(HttpStatus.OK).body(new ErrorMessage(request, HttpStatus.OK, "Aula atualizada com sucesso!"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id, @AuthenticationPrincipal JwtUserDetails userDetails, HttpServletRequest request) {
        try {
            aulaService.delete(id, userDetails.getRole());
            return ResponseEntity.status(HttpStatus.OK).body(new ErrorMessage(request, HttpStatus.OK, "Aula deletada com sucesso!"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id){
        try {
            Aula aula = aulaService.getById(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(AulaMapper.toDto(aula));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor");
        }
    }

    @GetMapping("/{unidadeId}/unidade")
    public ResponseEntity<?> getByUnidade(@PathVariable UUID unidadeId){
        try {
            List<Aula> aulas = aulaService.getByUnidade(unidadeId);
            return ResponseEntity.status(HttpStatus.CREATED).body(AulaMapper.toListDto(aulas));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor");
        }
    }
}
