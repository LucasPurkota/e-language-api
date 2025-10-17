package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.AlunoProfessor;
import com.tcc.e_language_api.entity.Perfil;
import com.tcc.e_language_api.web.dto.AlunoProfessorDto;

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
}
