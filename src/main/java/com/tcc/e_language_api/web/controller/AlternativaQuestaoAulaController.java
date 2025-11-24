package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.entity.AlternativaQuestaoAula;
import com.tcc.e_language_api.jwt.JwtUserDetails;
import com.tcc.e_language_api.service.AlternativaQuestaoAulaService;
import com.tcc.e_language_api.web.docs.AlternativaQuestaoAulaApiDocs;
import com.tcc.e_language_api.web.dto.AlternativaQuestaoAulaDto;
import com.tcc.e_language_api.web.dto.mapper.AlternativaQuestaoAulaMapper;
import com.tcc.e_language_api.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<?> create(@RequestBody AlternativaQuestaoAulaDto dto, @AuthenticationPrincipal JwtUserDetails userDetails, HttpServletRequest request) {
        try{
            alternativaQuestaoAulaService.create(AlternativaQuestaoAulaMapper.toEntity(dto), userDetails.getRole()) ;
            return ResponseEntity.status(HttpStatus.CREATED).body(new ErrorMessage(request, HttpStatus.CREATED, "Alternativa criada com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody AlternativaQuestaoAulaDto dto, @AuthenticationPrincipal JwtUserDetails userDetails, HttpServletRequest request) {
        try{
            alternativaQuestaoAulaService.update(id, dto, userDetails.getRole()); ;
            return ResponseEntity.status(HttpStatus.OK).body(new ErrorMessage(request, HttpStatus.OK, "Alternativa alterada com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id, @AuthenticationPrincipal JwtUserDetails userDetails, HttpServletRequest request) {
        try{
            alternativaQuestaoAulaService.delete(id, userDetails.getRole()); ;
            return ResponseEntity.status(HttpStatus.OK).body(new ErrorMessage(request, HttpStatus.CREATED, "Alternativa deletada com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id, HttpServletRequest request) {
        try{
            AlternativaQuestaoAula alternativaQuestaoAula = alternativaQuestaoAulaService.getById(id); ;
            return ResponseEntity.status(HttpStatus.OK).body(AlternativaQuestaoAulaMapper.toDto(alternativaQuestaoAula));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()));
        }
    }

    @GetMapping("/{questaoId}/questao")
    public ResponseEntity<?> getByQuestao(@PathVariable UUID questaoId, HttpServletRequest request) {
        try{
            List<AlternativaQuestaoAula> alternativaQuestaoAula = alternativaQuestaoAulaService.getByQuestao(questaoId); ;
            return ResponseEntity.status(HttpStatus.OK).body(AlternativaQuestaoAulaMapper.toListDto(alternativaQuestaoAula));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll(HttpServletRequest request) {
        try{
            List<AlternativaQuestaoAula> alternativaQuestaoAula = alternativaQuestaoAulaService.getAll();
            return ResponseEntity.status(HttpStatus.OK).body(AlternativaQuestaoAulaMapper.toListDto(alternativaQuestaoAula));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()));
        }
    }
}
