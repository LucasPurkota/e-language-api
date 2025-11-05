package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.AlunoUnidade;
import com.tcc.e_language_api.entity.Avaliacao;
import com.tcc.e_language_api.entity.AvaliacaoQuestaoAula;
import com.tcc.e_language_api.entity.QuestaoAula;
import com.tcc.e_language_api.exception.EntityNotFoundException;
import com.tcc.e_language_api.repository.AlunoUnidadeRepository;
import com.tcc.e_language_api.repository.AvaliacaoRepository;
import com.tcc.e_language_api.repository.QuestaoAulaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {
    private final AvaliacaoRepository avaliacaoRepository;
    private final QuestaoAulaRepository questaoAulaRepository;
    private final AlunoUnidadeRepository alunoUnidadeRepository;

    @Transactional
    public void create(UUID alunoUnidadeId) {
        AlunoUnidade alunoUnidade = alunoUnidadeRepository.findById(alunoUnidadeId)
                .orElseThrow(() -> new EntityNotFoundException("Aluno unidade não encontrada com ID: " + alunoUnidadeId));

        List<QuestaoAula> questoes = questaoAulaRepository.findTenQuestionsByUnidade(alunoUnidade.getUnidade().getUnidadeId());

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setAlunoUnidade(alunoUnidade);

        List<AvaliacaoQuestaoAula> questoesAvaliacao = new ArrayList<>();
        for (QuestaoAula questao : questoes) {
            AvaliacaoQuestaoAula avaliacaoQuestaoAula = new AvaliacaoQuestaoAula();
            avaliacaoQuestaoAula.setAvaliacao(avaliacao);
            avaliacaoQuestaoAula.setQuestaoAula(questao);
            questoesAvaliacao.add(avaliacaoQuestaoAula);
        }

        avaliacao.setAvaliacaoQuestaoAula(questoesAvaliacao);

        avaliacaoRepository.save(avaliacao);
    }

    //corrigir
    //salvarresposta
    //delete
    //getbyid
    //getbyunidade
}
