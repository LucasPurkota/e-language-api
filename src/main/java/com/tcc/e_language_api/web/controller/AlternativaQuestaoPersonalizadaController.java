package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.entity.AlternativaQuestaoAula;
import com.tcc.e_language_api.entity.AlternativaQuestaoPersonalizada;
import com.tcc.e_language_api.jwt.JwtUserDetails;
import com.tcc.e_language_api.service.AlternativaQuestaoPersonalizadaService;
import com.tcc.e_language_api.web.docs.AlternativaQuestaoPersonalizadaApiDocs;
import com.tcc.e_language_api.web.dto.AlternativaQuestaoAulaDto;
import com.tcc.e_language_api.web.dto.AlternativaQuestaoPersonalizadaDto;
import com.tcc.e_language_api.web.dto.mapper.AlternativaQuestaoAulaMapper;
import com.tcc.e_language_api.web.dto.mapper.AlternativaQuestaoPersonalizadaMapper;
import com.tcc.e_language_api.web.dto.mapper.AlunoProfessorMapper;
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
@RequestMapping("api/v1/alternativa-questao-personalizada")
@Tag(name = "Alternativa Questao Personalizada", description = "Operações relacionadas ao gerenciamento de alternativa para questao personalizada")
public class AlternativaQuestaoPersonalizadaController {
    private final AlternativaQuestaoPersonalizadaService alternativaQuestaoPersonalizadaService;

    @PostMapping
    @AlternativaQuestaoPersonalizadaApiDocs.Create
    public ResponseEntity<String> create(@RequestBody AlternativaQuestaoPersonalizadaDto dto, @AuthenticationPrincipal JwtUserDetails userDetails) {
        try{
            alternativaQuestaoPersonalizadaService.create(AlternativaQuestaoPersonalizadaMapper.toEntity(dto), userDetails.getRole()) ;
            return ResponseEntity.status(HttpStatus.CREATED).body("Alternativa criada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody AlternativaQuestaoPersonalizadaDto dto, @AuthenticationPrincipal JwtUserDetails userDetails) {
        try{
            alternativaQuestaoPersonalizadaService.update(id, dto, userDetails.getRole()); ;
            return ResponseEntity.status(HttpStatus.OK).body("Alternativa criada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id, @AuthenticationPrincipal JwtUserDetails userDetails) {
        try{
            alternativaQuestaoPersonalizadaService.delete(id, userDetails.getRole()); ;
            return ResponseEntity.status(HttpStatus.OK).body("Alternativa deletada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        try{
            AlternativaQuestaoPersonalizada alternativaQuestaoPersonalizada = alternativaQuestaoPersonalizadaService.getById(id); ;
            return ResponseEntity.status(HttpStatus.OK).body(AlternativaQuestaoPersonalizadaMapper.toDto(alternativaQuestaoPersonalizada));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{questaoId}/questao")
    public ResponseEntity<?> getByQuestao(@PathVariable UUID questaoId) {
        try{
            List<AlternativaQuestaoPersonalizada> alternativaQuestaoPersonalizada = alternativaQuestaoPersonalizadaService.getByQuestao(questaoId); ;
            return ResponseEntity.status(HttpStatus.OK).body(AlternativaQuestaoPersonalizadaMapper.toListDto(alternativaQuestaoPersonalizada));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
