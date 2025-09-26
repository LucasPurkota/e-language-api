package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.repository.PerfilRepository;
import com.tcc.e_language_api.service.PerfilService;
import com.tcc.e_language_api.web.dto.PerfilDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/perfil")
public class PerfilController {
    private final PerfilService perfilService;

    @PostConstruct
    public void init() {
        System.out.println("PerfilController inicializado");
    }

    @PostMapping
    public ResponseEntity<String> createPerfil(@RequestBody PerfilDto dto) {
        System.out.println("Método createPerfil chamado");
        try {
            perfilService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Perfil criado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
