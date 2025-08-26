package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.Aluno;
import com.tcc.e_language_api.web.dto.AlunoDto;
import com.tcc.e_language_api.web.dto.EnderecoDto;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

public class AlunoMapper {
  private final static ModelMapper modelMapper = new ModelMapper();
  public static Aluno toEntity(AlunoDto alunoDto, Aluno aluno) {
    alunoDto.setUsuarioId(aluno.getUsuarioId());
    modelMapper.map(alunoDto, aluno);
    aluno.getEnderecos().forEach(endereco -> {
        EnderecoDto dto = alunoDto.getEnderecos().get(aluno.getEnderecos().indexOf(endereco));
        modelMapper.map(dto, endereco);
        endereco.setUsuario(aluno);
    });
    return aluno;
  }

  public static AlunoDto toDto(Aluno aluno) {
    return modelMapper.map(aluno, AlunoDto.class);
  }

  public static List<AlunoDto> toListDto(List<Aluno> alunos) {
    return alunos.stream().map(aluno -> toDto(aluno)).collect(Collectors.toList());
  }
}
