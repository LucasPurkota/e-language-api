package com.tcc.e_language_api.web.controller;


import java.util.List;
import java.util.UUID;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.tcc.e_language_api.entity.Aluno;
import com.tcc.e_language_api.service.AlunoService;
import com.tcc.e_language_api.web.dto.AlunoDto;
import com.tcc.e_language_api.web.dto.mapper.AlunoMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/aluno")
public class AlunoController {
  private final AlunoService alunoService;
  @PostMapping
  public ResponseEntity<String> create(@RequestBody AlunoDto alunoDto) {
    alunoService.create(alunoDto);
    return ResponseEntity.status(HttpStatus.CREATED).body("Aluno criado com sucesso!");
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody AlunoDto alunoDto) {
    alunoService.update(id, alunoDto);
    return ResponseEntity.status(HttpStatus.OK).body("Aluno alterado com sucesso!");
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable UUID id) {
    alunoService.delete(id);
    return ResponseEntity.status(HttpStatus.OK).body("Aluno deletado com sucesso!");
  }

  @GetMapping("/{id}")
  public ResponseEntity<AlunoDto> getById(@PathVariable UUID id) {
    Aluno aluno = alunoService.getById(id);
    return ResponseEntity.status(HttpStatus.OK).body(AlunoMapper.toDto(aluno));
  }

//  @GetMapping("/{email}/email")
//  public ResponseEntity<AlunoDto> getByEmail(@PathVariable String email) {
//    Aluno aluno = alunoService.getByEmail(email);
//    return ResponseEntity.status(HttpStatus.OK).body(AlunoMapper.toDto(aluno));
//  }

  @GetMapping
  public ResponseEntity<List<AlunoDto>> getAll() {
    List<Aluno> aluno = alunoService.getAll();
    return ResponseEntity.status(HttpStatus.OK).body(AlunoMapper.toListDto(aluno));
  }
}
