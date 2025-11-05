package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.AlunoProfessor;
import com.tcc.e_language_api.entity.Perfil;
import com.tcc.e_language_api.service.AlunoProfessorService;
import com.tcc.e_language_api.web.dto.AlunoProfessorDto;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AlunoProfessorMapper {

    public static AlunoProfessor toEntity(AlunoProfessorDto dto) {
        Perfil aluno = new Perfil();
        aluno.setPerfilId(dto.getAlunoId());

        Perfil professor = new Perfil();
        professor.setPerfilId(dto.getProfessorId());

        AlunoProfessor alunoProfessor = new AlunoProfessor();
        alunoProfessor.setAluno(aluno);
        alunoProfessor.setProfessor(professor);

        return alunoProfessor;
    }

    public static AlunoProfessorDto toDto(AlunoProfessor alunoProfessor){
        AlunoProfessorDto dto = new AlunoProfessorDto();
        dto.setAlunoProfessorId(alunoProfessor.getAlunoProfessorId());
        dto.setAlunoId(alunoProfessor.getAluno().getPerfilId());
        dto.setProfessorId(alunoProfessor.getProfessor().getPerfilId());
        return dto;
    }

    public static List<AlunoProfessorDto> toListDto(List<AlunoProfessor> alunosProfessores) {
        return alunosProfessores.stream().map(alunoProfessor -> toDto(alunoProfessor)).collect(Collectors.toList());
    }
}
