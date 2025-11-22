package com.tcc.e_language_api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.tcc.e_language_api.entity.*;
import com.tcc.e_language_api.exception.EntityNotFoundException;
import com.tcc.e_language_api.repository.*;
import com.tcc.e_language_api.web.dto.NivelamentoRespostaDto;
import com.tcc.e_language_api.web.dto.RespostaQuestaoAulaDto;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NivelamentoService {
    private final NivelamentoRepository nivelamentoRepository;
    private final QuestaoAulaRepository questaoAulaRepository;
    private final PerfilIdiomaRepository perfilIdiomaRepository;
    private final PerfilIdiomaService perfilIdiomaService;
    private final NivelamentoQuestaoAulaRepository nivelamentoQuestaoAulaRepository;
    private final PerfilService perfilService;

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

    @Transactional
    public Nivelamento getById(UUID nivelamentoId) {
        return nivelamentoRepository.findById(nivelamentoId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Nivelamento com id %s não encontrado", nivelamentoId)));
    }

    @Transactional
    public List<NivelamentoQuestaoAula> corrigir(UUID nivelamentoId, UUID perfilIdiomaId,  List<NivelamentoRespostaDto> respostas) {
        List<NivelamentoQuestaoAula> listNivelamentoQuestaoAula = new ArrayList<>();
        Double pontos = 0.0;
        int acertos = 0;

        for (NivelamentoRespostaDto resposta : respostas) {
            NivelamentoQuestaoAula nivelamentoQuestaoAula = nivelamentoQuestaoAulaRepository.findById(resposta.getNivelamentoQuestaoAulaId())
                    .orElseThrow(() -> new EntityNotFoundException(String.format("Nivelamento Questao Aula com id %s não encontrado", resposta.getNivelamentoQuestaoAulaId())));
            nivelamentoQuestaoAula.setResposta(resposta.getResposta());
            if (resposta.getResposta().equals(resposta.getGabarito())) {
                pontos += 2;
                acertos++;
                nivelamentoQuestaoAula.setCorreto("S");
            } else {
                pontos -= 2;
                nivelamentoQuestaoAula.setCorreto("N");
            }
            listNivelamentoQuestaoAula.add(nivelamentoQuestaoAula);
        }

        PerfilIdioma perfilIdioma = perfilIdiomaService.getById(perfilIdiomaId);

        Double pontosIdioma = pontos + perfilIdioma.getPontosRanking();
        perfilIdioma.setPontosRanking(pontosIdioma);

        Perfil perfil = perfilService.getById(perfilIdioma.getPerfil().getPerfilId());

        Double pontosGeral = pontos + perfil.getPontosRanking();
        perfil.setPontosRanking(pontosGeral);

        Nivelamento nivelamento = getById(nivelamentoId);
        NivelIdioma nivelIdioma = new NivelIdioma();
        if (acertos <= 9){
            nivelIdioma.setNivelIdiomaId(1);
            nivelamento.setNivelIdioma(nivelIdioma);
            perfilIdioma.setNivelIdioma(nivelIdioma);
        } else if (acertos <= 18){
            nivelIdioma.setNivelIdiomaId(2);
            nivelamento.setNivelIdioma(nivelIdioma);
            perfilIdioma.setNivelIdioma(nivelIdioma);
        } else {
            nivelIdioma.setNivelIdiomaId(3);
            nivelamento.setNivelIdioma(nivelIdioma);
            perfilIdioma.setNivelIdioma(nivelIdioma);
        }

        Status status = new Status();
        status.setStatusId(3);
        nivelamento.setStatus(status);

        return listNivelamentoQuestaoAula;
    }

}
