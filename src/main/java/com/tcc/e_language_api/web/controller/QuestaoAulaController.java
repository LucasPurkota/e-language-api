package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.entity.QuestaoAula;
import com.tcc.e_language_api.jwt.JwtUserDetails;
import com.tcc.e_language_api.service.QuestaoAulaService;
import com.tcc.e_language_api.web.dto.QuestaoAulaDto;
import com.tcc.e_language_api.web.dto.mapper.QuestaoAulaMapper;
import com.tcc.e_language_api.web.exception.ErrorMessage;
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
@RequestMapping("api/v1/questao-aula")
public class QuestaoAulaController {
    private final QuestaoAulaService questaoAulaService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid QuestaoAulaDto dto, @AuthenticationPrincipal JwtUserDetails userDetails, HttpServletRequest request) {
        try{
            questaoAulaService.create(QuestaoAulaMapper.toEntity(dto), userDetails.getRole()) ;
            return ResponseEntity.status(HttpStatus.CREATED).body(new ErrorMessage(request, HttpStatus.CREATED, "Questão criada com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody @Valid QuestaoAulaDto dto, @AuthenticationPrincipal JwtUserDetails userDetails , HttpServletRequest request) {
        try{
            questaoAulaService.update(id, dto, userDetails.getRole());

            return ResponseEntity.status(HttpStatus.OK).body(new ErrorMessage(request, HttpStatus.OK, "Questão alterada com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id, @AuthenticationPrincipal JwtUserDetails userDetails , HttpServletRequest request) {
        try{
            questaoAulaService.delete(id, userDetails.getRole());

            return ResponseEntity.status(HttpStatus.OK).body(new ErrorMessage(request, HttpStatus.OK, "Questão deletada com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id, HttpServletRequest request) {
        try{
            QuestaoAula questaoAula = questaoAulaService.getById(id);

            return ResponseEntity.status(HttpStatus.OK).body(QuestaoAulaMapper.toDto(questaoAula));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    @GetMapping("/{aulaId}/aula")
    public ResponseEntity<?> getByAula(@PathVariable UUID aulaId, HttpServletRequest request) {
        try{
            List<QuestaoAula> questaoAula = questaoAulaService.getByAula(aulaId);

            return ResponseEntity.status(HttpStatus.OK).body(QuestaoAulaMapper.toListDto(questaoAula));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll(HttpServletRequest request) {
        try{
            List<QuestaoAula> questaoAula = questaoAulaService.getAll();

            return ResponseEntity.status(HttpStatus.OK).body(QuestaoAulaMapper.toListDto(questaoAula));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }
}
