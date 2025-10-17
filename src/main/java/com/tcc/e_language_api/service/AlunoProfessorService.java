package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.AlunoProfessor;
import com.tcc.e_language_api.repository.AlunoProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AlunoProfessorService {
    private final AlunoProfessorRepository alunoProfessorRepository;

    @Transactional
    public void create(AlunoProfessor alunoProfessor) {
        alunoProfessorRepository.save(alunoProfessor);
    }
}
