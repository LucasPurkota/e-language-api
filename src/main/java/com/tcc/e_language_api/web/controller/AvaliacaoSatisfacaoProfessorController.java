package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.jwt.JwtUserDetails;
import com.tcc.e_language_api.service.AvaliacaoSatisfacaoProfessorService;
import com.tcc.e_language_api.web.dto.AvaliacaoSatisfacaoProfessorDto;
import com.tcc.e_language_api.web.dto.mapper.AvaliacaoSatisfacaoProfessorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
