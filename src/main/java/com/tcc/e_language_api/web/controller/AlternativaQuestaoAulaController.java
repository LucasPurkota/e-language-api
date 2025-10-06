package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.entity.AlternativaQuestaoAula;
import com.tcc.e_language_api.jwt.JwtUserDetails;
import com.tcc.e_language_api.service.AlternativaQuestaoAulaService;
import com.tcc.e_language_api.web.dto.AlternativaQuestaoAulaDto;
import com.tcc.e_language_api.web.dto.mapper.AlternativaQuestaoAulaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/alternativa-questao-aula")
public class AlternativaQuestaoAulaController {
    private final AlternativaQuestaoAulaService alternativaQuestaoAulaService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody AlternativaQuestaoAulaDto dto, @AuthenticationPrincipal JwtUserDetails userDetails) {
        try{
            alternativaQuestaoAulaService.create(AlternativaQuestaoAulaMapper.toEntity(dto), userDetails.getRole()) ;
            return ResponseEntity.status(HttpStatus.CREATED).body("Alternativa questao aula criada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        try{
            List<AlternativaQuestaoAula> alternativaQuestaoAula = alternativaQuestaoAulaService.getAll();
            return ResponseEntity.status(HttpStatus.OK).body(AlternativaQuestaoAulaMapper.toListDto(alternativaQuestaoAula));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
