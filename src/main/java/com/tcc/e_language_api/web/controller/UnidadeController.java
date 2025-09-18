package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.jwt.JwtUserDetails;
import com.tcc.e_language_api.service.UnidadeService;
import com.tcc.e_language_api.web.dto.UnidadeDto;
import com.tcc.e_language_api.web.dto.mapper.UnidadeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/unidade")
public class UnidadeController {

    @Autowired
    private UnidadeService unidadeService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody UnidadeDto unidadeDto, @AuthenticationPrincipal JwtUserDetails userDetails) {
        try {
            unidadeService.create(UnidadeMapper.toEntity(unidadeDto), userDetails.getRole());
            return ResponseEntity.status(HttpStatus.CREATED).body("Unidade criada com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor");
        }
    }
}
