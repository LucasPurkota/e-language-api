package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.entity.QuestaoAula;
import com.tcc.e_language_api.entity.QuestaoPersonalizada;
import com.tcc.e_language_api.jwt.JwtUserDetails;
import com.tcc.e_language_api.service.QuestaoPersonalizadaService;
import com.tcc.e_language_api.web.docs.QuestaoPersonalizadaApiDocs;
import com.tcc.e_language_api.web.dto.QuestaoAulaDto;
import com.tcc.e_language_api.web.dto.QuestaoPersonalizadaDto;
import com.tcc.e_language_api.web.dto.RespostaQuestaoPersonalizadaDto;
import com.tcc.e_language_api.web.dto.mapper.QuestaoAulaMapper;
import com.tcc.e_language_api.web.dto.mapper.QuestaoPersonalizadaMapper;
import com.tcc.e_language_api.web.dto.mapper.RespostaQuestaoPersonalizadaMapper;
import com.tcc.e_language_api.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/questao-personalizada")
@Tag(name = "Questao Personalizada", description = "Operações relacionadas ao gerenciamento de questoes personalizada para alunos")
public class QuestaoPersonalizadaController {
    private final QuestaoPersonalizadaService questaoPersonalizadaService;

    @PostMapping
    @QuestaoPersonalizadaApiDocs.Create
    private ResponseEntity<?> create(@RequestBody QuestaoPersonalizadaDto dto, @AuthenticationPrincipal JwtUserDetails userDetails, HttpServletRequest request) {
        try{
            questaoPersonalizadaService.create(QuestaoPersonalizadaMapper.toEntity(dto), userDetails.getRole()) ;
            return ResponseEntity.status(HttpStatus.CREATED).body(new ErrorMessage(request, HttpStatus.CREATED, "Questão criada com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody @Valid QuestaoPersonalizadaDto dto, @AuthenticationPrincipal JwtUserDetails userDetails , HttpServletRequest request) {
        try{
            questaoPersonalizadaService.update(id, dto, userDetails.getRole());

            return ResponseEntity.status(HttpStatus.OK).body(new ErrorMessage(request, HttpStatus.OK, "Questão alterada com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id, @AuthenticationPrincipal JwtUserDetails userDetails , HttpServletRequest request) {
        try{
            questaoPersonalizadaService.delete(id, userDetails.getRole());

            return ResponseEntity.status(HttpStatus.OK).body(new ErrorMessage(request, HttpStatus.OK, "Questão deletada com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id, HttpServletRequest request) {
        try{
            QuestaoPersonalizada questao = questaoPersonalizadaService.getById(id);

            return ResponseEntity.status(HttpStatus.OK).body(QuestaoPersonalizadaMapper.toDto(questao));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    @GetMapping("/{alunoId}/aluno")
    public ResponseEntity<?> getByAula(@PathVariable UUID alunoId, @RequestParam(required = false) Integer statusId, @RequestParam(required = false) UUID idiomaId, HttpServletRequest request) {
        try{
            List<QuestaoPersonalizada> questao = questaoPersonalizadaService.getByAluno(alunoId, statusId, idiomaId);

            return ResponseEntity.status(HttpStatus.OK).body(QuestaoPersonalizadaMapper.toListDto(questao));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    @PutMapping("/corrigir/{alunoId}")
    public ResponseEntity<?> getByAula(@PathVariable UUID alunoId, @RequestBody List<RespostaQuestaoPersonalizadaDto> dto, HttpServletRequest request) {
        try{
            List<QuestaoPersonalizada> questao = questaoPersonalizadaService.corrigirAtividades(dto, alunoId);

            return ResponseEntity.status(HttpStatus.OK).body(RespostaQuestaoPersonalizadaMapper.toListDto(questao));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }
}
