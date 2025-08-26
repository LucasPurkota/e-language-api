package com.tcc.e_language_api.web.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.tcc.e_language_api.entity.Professor;
import com.tcc.e_language_api.web.dto.EnderecoDto;
import com.tcc.e_language_api.web.dto.ProfessorDto;

public class ProfessorMapper {

  private final static ModelMapper modelMapper = new ModelMapper();
  public static Professor toEntity(ProfessorDto professorDto, Professor professor) {
    professorDto.setUsuarioId(professor.getUsuarioId());
    modelMapper.map(professorDto, professor);
    professor.getEnderecos().forEach(endereco -> {
        EnderecoDto dto = professorDto.getEnderecos().get(professor.getEnderecos().indexOf(endereco));
        modelMapper.map(dto, endereco);
        endereco.setUsuario(professor);
    });
    return professor;
  }

  public static ProfessorDto toDto(Professor professor) {
    return modelMapper.map(professor, ProfessorDto.class);
  }

  public static List<ProfessorDto> toListDto(List<Professor> professores) {
    return professores.stream().map(professor -> toDto(professor)).collect(Collectors.toList());
  }
}
