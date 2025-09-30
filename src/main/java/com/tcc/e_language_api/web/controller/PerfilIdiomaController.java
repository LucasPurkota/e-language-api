package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.service.PerfilIdiomaService;
import com.tcc.e_language_api.web.dto.PerfilIdiomaDto;
import com.tcc.e_language_api.web.dto.mapper.PerfilIdiomaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/perfil-idioma")
public class PerfilIdiomaController {
    private final PerfilIdiomaService perfilIdiomaService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody PerfilIdiomaDto dto) {
        try {
            perfilIdiomaService.create(PerfilIdiomaMapper.toEntity(dto));
            return ResponseEntity.status(HttpStatus.CREATED).body("Perfil vinculado com idioma com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
