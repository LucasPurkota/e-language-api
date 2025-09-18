package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.Unidade;
import com.tcc.e_language_api.repository.UnidadeRepository;
import com.tcc.e_language_api.repository.IdiomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UnidadeService {

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Autowired
    private IdiomaRepository idiomaRepository;

    public void create(Unidade unidade, List<String> tipoPerfil) {
        if (!tipoPerfil.contains("ADMIN")) {
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
}
