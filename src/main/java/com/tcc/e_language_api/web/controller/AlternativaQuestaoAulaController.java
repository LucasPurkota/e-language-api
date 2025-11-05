package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.entity.AlternativaQuestaoAula;
import com.tcc.e_language_api.jwt.JwtUserDetails;
import com.tcc.e_language_api.service.AlternativaQuestaoAulaService;
import com.tcc.e_language_api.web.docs.AlternativaQuestaoAulaApiDocs;
import com.tcc.e_language_api.web.dto.AlternativaQuestaoAulaDto;
import com.tcc.e_language_api.web.dto.mapper.AlternativaQuestaoAulaMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/alternativa-questao-aula")
@Tag(name = "Alternativa Questao Aula", description = "Operações relacionadas ao gerenciamento de alternativa para questao aula")
public class AlternativaQuestaoAulaController {
    private final AlternativaQuestaoAulaService alternativaQuestaoAulaService;

    @PostMapping
    @AlternativaQuestaoAulaApiDocs.Create
    public ResponseEntity<String> create(@RequestBody AlternativaQuestaoAulaDto dto, @AuthenticationPrincipal JwtUserDetails userDetails) {
        try{
            alternativaQuestaoAulaService.create(AlternativaQuestaoAulaMapper.toEntity(dto), userDetails.getRole()) ;
            return ResponseEntity.status(HttpStatus.CREATED).body("Alternativa questao aula criada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody AlternativaQuestaoAulaDto dto, @AuthenticationPrincipal JwtUserDetails userDetails) {
        try{
            alternativaQuestaoAulaService.update(id, dto, userDetails.getRole()); ;
            return ResponseEntity.status(HttpStatus.OK).body("Alternativa criada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id, @AuthenticationPrincipal JwtUserDetails userDetails) {
        try{
            alternativaQuestaoAulaService.delete(id, userDetails.getRole()); ;
            return ResponseEntity.status(HttpStatus.OK).body("Alternativa deletada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        try{
            AlternativaQuestaoAula alternativaQuestaoAula = alternativaQuestaoAulaService.getById(id); ;
            return ResponseEntity.status(HttpStatus.OK).body(AlternativaQuestaoAulaMapper.toDto(alternativaQuestaoAula));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{questaoId}/questao")
    public ResponseEntity<?> getByQuestao(@PathVariable UUID questaoId) {
        try{
            List<AlternativaQuestaoAula> alternativaQuestaoAula = alternativaQuestaoAulaService.getByQuestao(questaoId); ;
            return ResponseEntity.status(HttpStatus.OK).body(AlternativaQuestaoAulaMapper.toListDto(alternativaQuestaoAula));
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
