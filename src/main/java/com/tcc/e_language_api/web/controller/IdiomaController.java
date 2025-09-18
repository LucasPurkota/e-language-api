package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.jwt.JwtUserDetails;
import com.tcc.e_language_api.service.IdiomaService;
import com.tcc.e_language_api.web.dto.IdiomaDto;
import com.tcc.e_language_api.web.dto.mapper.IdiomaMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/idioma")
public class IdiomaController {
    private final IdiomaService idiomaService;
    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid IdiomaDto idiomaDto, @AuthenticationPrincipal JwtUserDetails userDetails) {
        idiomaService.create(IdiomaMapper.toEntity(idiomaDto), userDetails.getRole());
        return ResponseEntity.status(HttpStatus.CREATED).body("Idioma criado com sucesso!");
    }
}
