package com.tcc.e_language_api.web.controller;

import java.util.UUID;

import com.tcc.e_language_api.entity.Nivelamento;
import com.tcc.e_language_api.web.dto.mapper.NivelamentoMapper;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/pendente")
    public ResponseEntity<?> getNivelamentoPendente(@RequestParam UUID perfilId, @RequestParam String idioma) {
        Nivelamento nivelamento = nivelamentoService.getNivelamentoPendente(perfilId, idioma);
        return ResponseEntity.status(HttpStatus.OK).body(NivelamentoMapper.toDto(nivelamento));
    }
}
