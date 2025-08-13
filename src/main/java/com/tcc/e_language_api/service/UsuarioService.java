package com.tcc.e_language_api.service;

import org.springframework.stereotype.Service;

import com.tcc.e_language_api.entity.Aluno;
import com.tcc.e_language_api.repository.AlunoRepository;
import com.tcc.e_language_api.web.dto.UsuarioDto;
import com.tcc.e_language_api.web.dto.mapper.AlunoMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioService {
  private final AlunoRepository alunoRepository;

  @Transactional
  public UsuarioDto create(UsuarioDto usuarioDto) {
    switch (usuarioDto.getTipo().toLowerCase()) {
      case "aluno":
      Aluno aluno = AlunoMapper.toEntity(usuarioDto);
        Aluno alunoSalvo = alunoRepository.save(aluno);
        System.out.println("Aluno salvo com sucesso: " + alunoSalvo.getUsuarioId());
        return usuarioDto;
    case "professor":
        return usuarioDto; // Implementar lógica para professor
    default:
      throw new IllegalArgumentException("Tipo de usuário inválido: " + usuarioDto.getTipo());
    }
    // return usuarioDto;
  }
}
