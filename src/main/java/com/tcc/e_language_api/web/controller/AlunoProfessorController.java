package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.entity.AlunoProfessor;
import com.tcc.e_language_api.jwt.JwtUserDetails;
import com.tcc.e_language_api.repository.AlunoProfessorRepository;
import com.tcc.e_language_api.service.AlunoProfessorService;
import com.tcc.e_language_api.web.docs.AlunoProfessorApiDocs;
import com.tcc.e_language_api.web.dto.AlunoProfessorDto;
import com.tcc.e_language_api.web.dto.mapper.AlternativaQuestaoAulaMapper;
import com.tcc.e_language_api.web.dto.mapper.AlunoProfessorMapper;
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
@RequestMapping("api/v1/aluno-professor")
@Tag(name = "Aluno Professor", description = "Operações relacionadas ao gerenciamento aluno professor")
public class AlunoProfessorController {
    private final AlunoProfessorService alunoProfessorService;

    @PostMapping
    @AlunoProfessorApiDocs.Create
    public ResponseEntity<?> vincularAoProfessor(@RequestBody AlunoProfessorDto dto, HttpServletRequest request) {
        try{
            alunoProfessorService.create(AlunoProfessorMapper.toEntity(dto)) ;
            return ResponseEntity.status(HttpStatus.CREATED).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, "Aluno vinculado a um professor!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id, HttpServletRequest request) {
        try{
            alunoProfessorService.delete(id); ;
            return ResponseEntity.status(HttpStatus.OK).body(new ErrorMessage(request, HttpStatus.OK, "vinculo excluido com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id, HttpServletRequest request) {
        try{
            AlunoProfessor alunoProfessor = alunoProfessorService.getById(id); ;
            return ResponseEntity.status(HttpStatus.OK).body(AlunoProfessorMapper.toDto(alunoProfessor));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll(HttpServletRequest request) {
        try{
            List<AlunoProfessor> alunoProfessor = alunoProfessorService.getAll(); ;
            return ResponseEntity.status(HttpStatus.OK).body(AlunoProfessorMapper.toListDto(alunoProfessor));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()));
        }
    }
}
