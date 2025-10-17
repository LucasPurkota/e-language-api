package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.jwt.JwtUserDetails;
import com.tcc.e_language_api.service.QuestaoPersonalizadaService;
import com.tcc.e_language_api.web.docs.QuestaoPersonalizadaApiDocs;
import com.tcc.e_language_api.web.dto.QuestaoPersonalizadaDto;
import com.tcc.e_language_api.web.dto.mapper.QuestaoAulaMapper;
import com.tcc.e_language_api.web.dto.mapper.QuestaoPersonalizadaMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("api/v1/questao-personalizada")
@Tag(name = "Questao Personalizada", description = "Operações relacionadas ao gerenciamento de questoes personalizada para alunos")
public class QuestaoPersonalizadaController {
    private final QuestaoPersonalizadaService questaoPersonalizadaService;

    @PostMapping
    @QuestaoPersonalizadaApiDocs.Create
    private ResponseEntity<String> create(@RequestBody QuestaoPersonalizadaDto dto, @AuthenticationPrincipal JwtUserDetails userDetails) {
        try{
            questaoPersonalizadaService.create(QuestaoPersonalizadaMapper.toEntity(dto), userDetails.getRole()) ;
            return ResponseEntity.status(HttpStatus.CREATED).body("Questao criada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
