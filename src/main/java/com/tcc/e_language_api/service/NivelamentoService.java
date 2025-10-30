package com.tcc.e_language_api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.tcc.e_language_api.entity.*;
import com.tcc.e_language_api.repository.*;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NivelamentoService {
    private final NivelamentoRepository nivelamentoRepository;
    private final QuestaoAulaRepository questaoAulaRepository;
    private final PerfilRepository perfilRepository;
    private final IdiomaRepository idiomaRepository;
    private final PerfilIdiomaRepository perfilIdiomaRepository;

    @Transactional
    public void create(String idioma, UUID perfilId) {
        PerfilIdioma perfilIdioma = perfilIdiomaRepository.findByIdiomaAndPerfil(idioma, perfilId);

        List<QuestaoAula> questoes = new ArrayList<>(); 
        questoes.addAll(questaoAulaRepository.findThreeQuestionByNivel(idioma, 1, 1));
        questoes.addAll(questaoAulaRepository.findThreeQuestionByNivel(idioma, 2, 1));
        questoes.addAll(questaoAulaRepository.findThreeQuestionByNivel(idioma, 3, 1));
        questoes.addAll(questaoAulaRepository.findThreeQuestionByNivel(idioma, 1, 2));
        questoes.addAll(questaoAulaRepository.findThreeQuestionByNivel(idioma, 2, 2));
        questoes.addAll(questaoAulaRepository.findThreeQuestionByNivel(idioma, 3, 2));
        questoes.addAll(questaoAulaRepository.findThreeQuestionByNivel(idioma, 1, 3));
        questoes.addAll(questaoAulaRepository.findThreeQuestionByNivel(idioma, 2, 3));
        questoes.addAll(questaoAulaRepository.findThreeQuestionByNivel(idioma, 3, 3));

        Nivelamento nivelamento = new Nivelamento();
        nivelamento.setPerfilIdioma(perfilIdioma);
        nivelamento.setDataRealizacao(LocalDateTime.now());

        List<NivelamentoQuestaoAula> nivelamentoQuestaoAula = new ArrayList<>();
        for (QuestaoAula questaoAula : questoes) {
            NivelamentoQuestaoAula nivQuestAula = new NivelamentoQuestaoAula();
            nivQuestAula.setNivelamento(nivelamento);
            nivQuestAula.setQuestaoAula(questaoAula);
            nivelamentoQuestaoAula.add(nivQuestAula);
        }

        nivelamento.setNivelamentoQuestaoAulas(nivelamentoQuestaoAula);
        Status status = new Status();
        status.setStatusId(1);
        nivelamento.setStatus(status);
        nivelamentoRepository.save(nivelamento);

    }

    @Transactional
    public Nivelamento getNivelamentoPendente(UUID perfilId, String idioma) {
        return nivelamentoRepository.findNivelamentoPendente(idioma, perfilId);
    }
}
