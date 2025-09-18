package com.tcc.e_language_api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tcc.e_language_api.entity.Idioma;
import com.tcc.e_language_api.entity.Nivelamento;
import com.tcc.e_language_api.entity.NivelamentoQuestaoAula;
import com.tcc.e_language_api.entity.Perfil;
import com.tcc.e_language_api.entity.QuestaoAula;
import com.tcc.e_language_api.repository.IdiomaRepository;
import com.tcc.e_language_api.repository.NivelamentoRepository;
import com.tcc.e_language_api.repository.PerfilRepository;
import com.tcc.e_language_api.repository.QuestaoAulaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NivelamentoService {
    private final NivelamentoRepository nivelamentoRepository;
    private final QuestaoAulaRepository questaoAulaRepository;
    private final PerfilRepository perfilRepository;
    private final IdiomaRepository idiomaRepository;

    @Transactional
    public void create(String nomeIdioma, UUID perfilId) {
        Perfil perfil = perfilRepository.findById(perfilId).orElseThrow(() -> new RuntimeException("Usuario not found"));
        Idioma idioma = idiomaRepository.findByNome(nomeIdioma).orElseThrow(() -> new RuntimeException("Idioma not found"));
        

        List<QuestaoAula> questoes = new ArrayList<>(); 
        questoes.addAll(questaoAulaRepository.findFiveQuestionByNivel(nomeIdioma, 1));
        questoes.addAll(questaoAulaRepository.findFiveQuestionByNivel(nomeIdioma, 2));
        questoes.addAll(questaoAulaRepository.findFiveQuestionByNivel(nomeIdioma, 3));
        
        Nivelamento nivelamento = new Nivelamento();
        nivelamento.setPerfil(perfil);
        nivelamento.setIdioma(idioma);
        nivelamento.setDataRealizacao(LocalDateTime.now());

        List<NivelamentoQuestaoAula> nivelamentoQuestaoAula = new ArrayList<>();
        for (QuestaoAula questaoAula : questoes) {
            NivelamentoQuestaoAula nivQuestAula = new NivelamentoQuestaoAula();
            nivQuestAula.setNivelamento(nivelamento);
            nivQuestAula.setQuestaoAula(questaoAula);
            nivelamentoQuestaoAula.add(nivQuestAula);
        }

        nivelamento.setNivelamentoQuestaoAulas(nivelamentoQuestaoAula);
        nivelamentoRepository.save(nivelamento);

    }
    
}
