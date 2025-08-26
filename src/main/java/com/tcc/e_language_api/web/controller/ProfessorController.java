package com.tcc.e_language_api.web.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.tcc.e_language_api.entity.Professor;
import com.tcc.e_language_api.service.ProfessorService;
import com.tcc.e_language_api.web.dto.ProfessorDto;
import com.tcc.e_language_api.web.dto.mapper.ProfessorMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/professor")
public class ProfessorController {
private final ProfessorService professorService;
  @PostMapping
  public ResponseEntity<String> create(@RequestBody ProfessorDto professorDto) {
    professorService.create(professorDto);
    return ResponseEntity.status(HttpStatus.CREATED).body("Professor criado com sucesso!");
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody ProfessorDto professorDto) {
    professorService.update(id, professorDto);
    return ResponseEntity.status(HttpStatus.OK).body("Professor alterado com sucesso!");
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable UUID id) {
    professorService.delete(id);
    return ResponseEntity.status(HttpStatus.OK).body("Professor deletado com sucesso!");
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProfessorDto> getById(@PathVariable UUID id) {
    Professor professor = professorService.getById(id);
    return ResponseEntity.status(HttpStatus.OK).body(ProfessorMapper.toDto(professor));
  }


  @GetMapping
  public ResponseEntity<List<ProfessorDto>> getAll() {
    List<Professor> professor = professorService.getAll();
    return ResponseEntity.status(HttpStatus.OK).body(ProfessorMapper.toListDto(professor));
  }
}
