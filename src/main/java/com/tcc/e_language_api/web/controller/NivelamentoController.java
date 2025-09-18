package com.tcc.e_language_api.web.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.e_language_api.service.NivelamentoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/nivelamento")
public class NivelamentoController {
    private final NivelamentoService nivelamentoService;
    @PostMapping
    public ResponseEntity<String> create(@RequestParam UUID perfilId, @RequestParam String idioma) {
        nivelamentoService.create(idioma, perfilId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Aluno criado com sucesso!");
    }
}
