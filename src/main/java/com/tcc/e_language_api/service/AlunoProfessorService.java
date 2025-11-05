package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.AlunoProfessor;
import com.tcc.e_language_api.repository.AlunoProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AlunoProfessorService {
    private final AlunoProfessorRepository alunoProfessorRepository;

    @Transactional
    public void create(AlunoProfessor alunoProfessor) {
        alunoProfessorRepository.save(alunoProfessor);
    }

    @Transactional
    public void delete(UUID id) {
        getById(id);
        alunoProfessorRepository.deleteById(id);
    }

    @Transactional
    public AlunoProfessor getById(UUID id) {
        return alunoProfessorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno Professor not found"));
    }

    @Transactional
    public List<AlunoProfessor> getAll() {
        return alunoProfessorRepository.findAll();
    }
}
