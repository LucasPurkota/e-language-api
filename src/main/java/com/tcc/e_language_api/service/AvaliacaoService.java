package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.*;
import com.tcc.e_language_api.exception.EntityNotFoundException;
import com.tcc.e_language_api.repository.*;
import com.tcc.e_language_api.web.dto.AvaliacaoQuestaoAulaDto;
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
    private final PerfilService perfilService;
    private final AvaliacaoQuestaoAulaRepository avaliacaoQuestaoAulaRepository;
    private final PerfilIdiomaService perfilIdiomaService;

    @Transactional
    public void create(UUID unidadeId, String usuario) {
        Perfil aluno = perfilService.getByUsuarioAndTipoPerfil("Aluno", usuario);
        AlunoUnidade alunoUnidade = alunoUnidadeRepository.findByAlunoAndUnidade(aluno.getPerfilId(), unidadeId)
                .orElseThrow(() -> new EntityNotFoundException("Aluno unidade não encontrado"));

        List<QuestaoAula> questoes = questaoAulaRepository.findTenQuestionsByUnidade(alunoUnidade.getUnidade().getUnidadeId());

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setAlunoUnidade(alunoUnidade);

        Status status = new Status();
        status.setStatusId(1);

        avaliacao.setStatus(status);

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

    @Transactional
    public List<Avaliacao> getByUnidade(UUID unidadeId, String usuario) {
        Perfil aluno = perfilService.getByUsuarioAndTipoPerfil("Aluno", usuario);

        AlunoUnidade alunoUnidade = alunoUnidadeRepository.findByAlunoAndUnidade(aluno.getPerfilId(), unidadeId)
                .orElseThrow(() -> new EntityNotFoundException("Aluno unidade não encontrado"));

        return avaliacaoRepository.findByAlunoUnidadeAlunoUnidadeId(alunoUnidade.getAlunoUnidadeId());
    }

    @Transactional
    public Avaliacao getPendente(UUID unidadeId, String usuario) {
        Perfil aluno = perfilService.getByUsuarioAndTipoPerfil("Aluno", usuario);

        AlunoUnidade alunoUnidade = alunoUnidadeRepository.findByAlunoAndUnidade(aluno.getPerfilId(), unidadeId)
                .orElseThrow(() -> new EntityNotFoundException("Aluno unidade não encontrado"));

        return avaliacaoRepository.findPendente(alunoUnidade.getAlunoUnidadeId())
                .orElseThrow(() -> new EntityNotFoundException("Nenhuma avaliação pendente encontrada"));
    }


    @Transactional
    public List<AvaliacaoQuestaoAula> corrigir(UUID avaliacaoId, UUID unidadeId, String usuario, List<AvaliacaoQuestaoAulaDto> respostas) {
        Perfil aluno = perfilService.getByUsuarioAndTipoPerfil("Aluno", usuario);
        Avaliacao avaliacao = getById(avaliacaoId);

        AlunoUnidade alunoUnidade = alunoUnidadeRepository.findByAlunoAndUnidade(aluno.getPerfilId(), unidadeId)
                .orElseThrow(() -> new EntityNotFoundException("Aluno unidade não encontrado"));

        Double pontos = 0.0;
        int qtdAcertos = 0;
        List<AvaliacaoQuestaoAula> avaliacaoQuestaoAulaList = new ArrayList<>();
        for (AvaliacaoQuestaoAulaDto dto : respostas) {
            AvaliacaoQuestaoAula questao = avaliacaoQuestaoAulaRepository.findById(dto.getAvaliacaoQuestaoAulaId())
                    .orElseThrow(() -> new RuntimeException("Avaliacao questao aula não encontrado"));

            questao.setResposta(dto.getResposta());
            if (dto.getResposta().equals(questao.getQuestaoAula().getGabarito())) {
                qtdAcertos ++;
                pontos += 2;
                questao.setCorreto("S");
            }else {
                pontos -= 2;
                questao.setCorreto("N");
            }

            avaliacaoQuestaoAulaList.add(questao);
        }

        Status status = new Status();
        status.setStatusId(3);
        avaliacao.setStatus(status);

        if (qtdAcertos >= 6) {
            alunoUnidade.setStatus(status);
            avaliacao.setAprovado("S");
            pontos += 10;
        } else {
            avaliacao.setAprovado("N");
            pontos -= 10;
        }

        PerfilIdioma perfilIdioma = perfilIdiomaService.getByPerfilAndIdioma(aluno.getPerfilId(), alunoUnidade.getUnidade().getIdioma().getIdiomaId());
        Double pontosPerfil = pontos + perfilIdioma.getPontosRanking();
        perfilIdioma.setPontosRanking(pontosPerfil);

        Double pontosGeral = pontos + aluno.getPontosRanking();
        aluno.setPontosRanking(pontosGeral);

        return avaliacaoQuestaoAulaList;
    }

    @Transactional
    public Avaliacao getById(UUID avaliacaoId) {
        return avaliacaoRepository.findByIdNative(avaliacaoId)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada"));
    }
}
