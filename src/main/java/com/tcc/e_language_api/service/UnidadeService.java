package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.NivelIdioma;
import com.tcc.e_language_api.entity.Unidade;
import com.tcc.e_language_api.exception.EntityNotFoundException;
import com.tcc.e_language_api.repository.UnidadeRepository;
import com.tcc.e_language_api.repository.IdiomaRepository;
import com.tcc.e_language_api.web.dto.UnidadeDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class UnidadeService {

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Autowired
    private IdiomaRepository idiomaRepository;

    @Transactional
    public void create(Unidade unidade, List<String> tipoPerfil) {
        if (!tipoPerfil.contains("Admin")) {
            throw new RuntimeException("Usario não contem permisão para essa tarefa");
        }

        if (unidadeRepository.existsByTitulo(unidade.getTitulo())) {
            throw new RuntimeException("Já existe uma unidade com este título");
        }

        if (unidadeRepository.existsByConteudo(unidade.getConteudo())) {
            throw new RuntimeException("Já existe uma unidade com este conteúdo");
        }

        if (!idiomaRepository.existsById(unidade.getIdioma().getIdiomaId())) {
            throw new RuntimeException("Idioma não encontrado");
        }

        unidadeRepository.save(unidade);
    }

    @Transactional
    public void update(UUID unidadeId, UnidadeDto dto, List<String> tipoPerfil) {
        if (!tipoPerfil.contains("Admin")) {
            throw new RuntimeException("Usario não contem permisão para essa tarefa");
        }

        Unidade unidade = getById(unidadeId);

        NivelIdioma nivelIdioma = new NivelIdioma();
        nivelIdioma.setNivelIdiomaId(dto.getNivelIdiomaId());
        unidade.setNivelIdioma(nivelIdioma);
        unidade.setTitulo(dto.getTitulo());
        unidade.setNumero(dto.getNumero());
        unidade.setConteudo(dto.getConteudo());
    }

    @Transactional
    public void delete(UUID unidadeId, List<String> tipoPerfil) {
        if (!tipoPerfil.contains("Admin")) {
            throw new RuntimeException("Usario não contem permisão para essa tarefa");
        }

        getById(unidadeId);

        unidadeRepository.deleteById(unidadeId);
    }

    @Transactional
    public Unidade getById(UUID id) {
        return unidadeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unidade não encontrada com ID: " + id));
    }

    @Transactional
    public List<Unidade> getAll() {
        return unidadeRepository.findAll();
    }

    @Transactional
    public List<Unidade> getByIdiomaId(UUID idiomaId) {
        if (!idiomaRepository.existsById(idiomaId)) {
            throw new EntityNotFoundException("Idioma não encontrado com ID: " + idiomaId);
        }
        return unidadeRepository.findByIdiomaIdiomaId(idiomaId);
    }
}
