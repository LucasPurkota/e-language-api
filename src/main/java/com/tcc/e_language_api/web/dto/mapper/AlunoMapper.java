package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.Aluno;
import com.tcc.e_language_api.web.dto.UsuarioDto;

import org.modelmapper.ModelMapper;

public class AlunoMapper {
  public static Aluno toEntity(UsuarioDto usuarioDto) {
    return new ModelMapper().map(usuarioDto, Aluno.class);
  }
}
