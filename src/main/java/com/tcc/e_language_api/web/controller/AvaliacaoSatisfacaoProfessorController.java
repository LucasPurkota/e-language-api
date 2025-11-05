package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.entity.AvaliacaoSatisfacaoProfessor;
import com.tcc.e_language_api.jwt.JwtUserDetails;
import com.tcc.e_language_api.service.AvaliacaoSatisfacaoProfessorService;
import com.tcc.e_language_api.web.dto.AvaliacaoSatisfacaoProfessorDto;
import com.tcc.e_language_api.web.dto.mapper.AvaliacaoSatisfacaoProfessorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/avaliacao-satisfacao-professor")
@RequiredArgsConstructor
public class AvaliacaoSatisfacaoProfessorController {
    private final AvaliacaoSatisfacaoProfessorService avaliacaoSatisfacaoProfessorService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody AvaliacaoSatisfacaoProfessorDto dto, @AuthenticationPrincipal JwtUserDetails userDetails) {
        try {
            avaliacaoSatisfacaoProfessorService.create(AvaliacaoSatisfacaoProfessorMapper.toEntity(dto));
            return ResponseEntity.status(HttpStatus.CREATED).body("Avaliação satisfacao criada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody AvaliacaoSatisfacaoProfessorDto dto) {
        try {
            avaliacaoSatisfacaoProfessorService.update(id, dto);
            return ResponseEntity.status(HttpStatus.OK).body("Avaliação satisfacao alterada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        try {
            avaliacaoSatisfacaoProfessorService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Avaliação satisfacao deletada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        try {
            AvaliacaoSatisfacaoProfessor avaliacaoSatisfacaoProfessor = avaliacaoSatisfacaoProfessorService.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(AvaliacaoSatisfacaoProfessorMapper.toDto(avaliacaoSatisfacaoProfessor));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{professorId}/professor")
    public ResponseEntity<?> getByProfessor(@PathVariable UUID professorId) {
        try {
            List<AvaliacaoSatisfacaoProfessor> avaliacaoSatisfacaoProfessor = avaliacaoSatisfacaoProfessorService.getByProfessor(professorId);
            return ResponseEntity.status(HttpStatus.OK).body(AvaliacaoSatisfacaoProfessorMapper.toListDto(avaliacaoSatisfacaoProfessor));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
