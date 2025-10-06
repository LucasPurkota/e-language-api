package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.entity.Idioma;
import com.tcc.e_language_api.jwt.JwtUserDetails;
import com.tcc.e_language_api.service.IdiomaService;
import com.tcc.e_language_api.web.docs.IdiomaApiDocs;
import com.tcc.e_language_api.web.dto.IdiomaDto;
import com.tcc.e_language_api.web.dto.mapper.IdiomaMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/idioma")
@Tag(name = "4. Idiomas", description = "Operações relacionadas ao gerenciamento de idiomas")
public class IdiomaController {
    private final IdiomaService idiomaService;
    
    @PostMapping
    @IdiomaApiDocs.CreateIdioma
    public ResponseEntity<String> create(@RequestBody @Valid IdiomaDto idiomaDto, @AuthenticationPrincipal JwtUserDetails userDetails) {
        idiomaService.create(IdiomaMapper.toEntity(idiomaDto), userDetails.getRole());
        return ResponseEntity.status(HttpStatus.CREATED).body("Idioma criado com sucesso!");
    }

    @GetMapping
    @IdiomaApiDocs.GetAll
    public ResponseEntity<List<IdiomaDto>> getAll() {
        List<Idioma> idiomas = idiomaService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(IdiomaMapper.toListDto(idiomas));
    }
}
