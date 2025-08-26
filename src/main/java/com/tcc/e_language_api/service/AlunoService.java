package com.tcc.e_language_api.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tcc.e_language_api.entity.Aluno;
import com.tcc.e_language_api.repository.AlunoRepository;
import com.tcc.e_language_api.web.dto.AlunoDto;
import com.tcc.e_language_api.web.dto.mapper.AlunoMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AlunoService {
  private final AlunoRepository alunoRepository;

  @Transactional
  public void create(AlunoDto alunoDto) {
    try {
      if (!alunoDto.validarCpf()) {
        throw new RuntimeException("CPF inválido");
      }
      alunoRepository.save(AlunoMapper.toEntity(alunoDto, new Aluno()));

    } catch (Exception e) {
      throw new RuntimeException("Error creating Aluno: " + e.getMessage());
    }
  }

  @Transactional
  public void update(UUID id, AlunoDto alunoDto) {
    try {
      if (!alunoDto.validarCpf()) {
        throw new RuntimeException("CPF inválido");
      }

      Aluno aluno = getById(id);

      aluno = AlunoMapper.toEntity(alunoDto, aluno);
    } catch (Exception e) {
      throw new RuntimeException("Error creating Aluno: " + e.getMessage());
    }
  }

  
  @Transactional
  public void delete(UUID id) {
    try {
      // getById(id);

      alunoRepository.deleteById(id);

    } catch (Exception e) {
      throw new RuntimeException("Error creating Aluno: " + e.getMessage());
    }
  }

  @Transactional
  public Aluno getById(UUID id) {
    try {
      return alunoRepository.findById(id)
          .orElseThrow(() -> new RuntimeException("Aluno not found"));


    } catch (Exception e) {
      throw new RuntimeException("Error creating Aluno: " + e.getMessage());
    }
  }

  @Transactional
  public List<Aluno> getAll() {
    try {
      return alunoRepository.findAll();
    } catch (Exception e) {
      throw new RuntimeException("Error fetching Alunos: " + e.getMessage());
    }
  }
}
