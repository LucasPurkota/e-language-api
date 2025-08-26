package com.tcc.e_language_api.service;

import com.tcc.e_language_api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import com.tcc.e_language_api.entity.Aluno;
import com.tcc.e_language_api.entity.Usuario;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioService {

  @Transactional
  public Usuario getByEmail(String email) {
    try {
      return UsuarioRepository.findByEmail(email);
    } catch (Exception e) {
      throw new RuntimeException("Error fetching Alunos: " + e.getMessage());
    }
  }
}
