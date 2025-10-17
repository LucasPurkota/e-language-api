package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.jwt.JwtUserDetails;
import com.tcc.e_language_api.service.AvaliacaoService;
import com.tcc.e_language_api.web.dto.AvaliacaoDto;
import com.tcc.e_language_api.web.dto.mapper.QuestaoPersonalizadaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/avaliacao")
@RequiredArgsConstructor
public class AvaliacaoController {
    private final AvaliacaoService avaliacaoService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody AvaliacaoDto dto, @AuthenticationPrincipal JwtUserDetails userDetails){
        try{
            avaliacaoService.create(dto.getAlunoUnidadeId());
            return ResponseEntity.status(HttpStatus.CREATED).body("Avaliação criada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
