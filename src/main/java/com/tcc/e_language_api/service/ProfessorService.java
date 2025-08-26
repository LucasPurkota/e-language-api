package com.tcc.e_language_api.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tcc.e_language_api.entity.Professor;
import com.tcc.e_language_api.repository.ProfessorRepository;
import com.tcc.e_language_api.web.dto.ProfessorDto;
import com.tcc.e_language_api.web.dto.mapper.ProfessorMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProfessorService {
private final ProfessorRepository professorRepository;

  @Transactional
  public void create(ProfessorDto professorDto) {
    try {
      if (!professorDto.validarCpf()) {
        throw new RuntimeException("CPF inválido");
      }
      professorRepository.save(ProfessorMapper.toEntity(professorDto, new Professor()));

    } catch (Exception e) {
      throw new RuntimeException("Error creating Professor: " + e.getMessage());
    }
  }

  @Transactional
  public void update(UUID id, ProfessorDto ProfessorDto) {
    try {
      if (!ProfessorDto.validarCpf()) {
        throw new RuntimeException("CPF inválido");
      }

      Professor professor = getById(id);

      professor = ProfessorMapper.toEntity(ProfessorDto, professor);
    } catch (Exception e) {
      throw new RuntimeException("Error creating Professor: " + e.getMessage());
    }
  }

  
  @Transactional
  public void delete(UUID id) {
    try {
      // getById(id);

      professorRepository.deleteById(id);

    } catch (Exception e) {
      throw new RuntimeException("Error creating Professor: " + e.getMessage());
    }
  }

  @Transactional
  public Professor getById(UUID id) {
    try {
      return professorRepository.findById(id)
          .orElseThrow(() -> new RuntimeException("Professor not found"));


    } catch (Exception e) {
      throw new RuntimeException("Error creating Professor: " + e.getMessage());
    }
  }

  @Transactional
  public List<Professor> getAll() {
    try {
      return professorRepository.findAll();
    } catch (Exception e) {
      throw new RuntimeException("Error fetching Professores: " + e.getMessage());
    }
  }
}
