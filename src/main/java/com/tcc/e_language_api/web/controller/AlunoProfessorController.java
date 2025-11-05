package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.entity.AlunoProfessor;
import com.tcc.e_language_api.jwt.JwtUserDetails;
import com.tcc.e_language_api.repository.AlunoProfessorRepository;
import com.tcc.e_language_api.service.AlunoProfessorService;
import com.tcc.e_language_api.web.docs.AlunoProfessorApiDocs;
import com.tcc.e_language_api.web.dto.AlunoProfessorDto;
import com.tcc.e_language_api.web.dto.mapper.AlternativaQuestaoAulaMapper;
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
@RequestMapping("api/v1/aluno-professor")
@Tag(name = "Aluno Professor", description = "Operações relacionadas ao gerenciamento aluno professor")
public class AlunoProfessorController {
    private final AlunoProfessorService alunoProfessorService;

    @PostMapping
    @AlunoProfessorApiDocs.Create
    public ResponseEntity<String> vincularAoProfessor(@RequestBody AlunoProfessorDto dto, @AuthenticationPrincipal JwtUserDetails userDetails) {
        try{
            alunoProfessorService.create(AlunoProfessorMapper.toEntity(dto)) ;
            return ResponseEntity.status(HttpStatus.CREATED).body("Aluno vinculado a um professor!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        try{
            alunoProfessorService.delete(id); ;
            return ResponseEntity.status(HttpStatus.CREATED).body("vinculo excluido com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        try{
            AlunoProfessor alunoProfessor = alunoProfessorService.getById(id); ;
            return ResponseEntity.status(HttpStatus.CREATED).body(AlunoProfessorMapper.toDto(alunoProfessor));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        try{
            List<AlunoProfessor> alunoProfessor = alunoProfessorService.getAll(); ;
            return ResponseEntity.status(HttpStatus.CREATED).body(AlunoProfessorMapper.toListDto(alunoProfessor));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
