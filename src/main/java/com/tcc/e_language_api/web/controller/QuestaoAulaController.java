package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.entity.QuestaoAula;
import com.tcc.e_language_api.jwt.JwtUserDetails;
import com.tcc.e_language_api.service.QuestaoAulaService;
import com.tcc.e_language_api.web.dto.QuestaoAulaDto;
import com.tcc.e_language_api.web.dto.UsuarioDto;
import com.tcc.e_language_api.web.dto.mapper.QuestaoAulaMapper;
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
@RequestMapping("api/v1/questao-aula")
public class QuestaoAulaController {
    private final QuestaoAulaService questaoAulaService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid QuestaoAulaDto questaoAulaDto, @AuthenticationPrincipal JwtUserDetails userDetails) {
        try{
            questaoAulaService.create(QuestaoAulaMapper.toEntity(questaoAulaDto), userDetails.getRole()) ;
            return ResponseEntity.status(HttpStatus.CREATED).body("Aluno criado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
