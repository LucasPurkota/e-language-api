package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.*;
import com.tcc.e_language_api.repository.QuestaoPersonalizadaRepository;
import com.tcc.e_language_api.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestaoPersonalizadaService {
    private final QuestaoPersonalizadaRepository questaoPersonalizadaRepository;
    private final PerfilService perfilService;
    private final PerfilIdiomaService perfilIdiomaService;

    @Transactional
    public void create(QuestaoPersonalizada questaoPersonalizada, List<String> perfil) {
        if (!perfil.contains("Professor")) {
            throw new RuntimeException("Você não tem permissão para realizar esta tarefa");
        }

        questaoPersonalizadaRepository.save(questaoPersonalizada);
    }

    @Transactional
    public void update(UUID questaoId, QuestaoPersonalizadaDto dto, List<String> tipoPerfil) {
        if (!tipoPerfil.contains("Professor")) {
            throw new RuntimeException("Usario não contem permisão para essa tarefa");
        }

        QuestaoPersonalizada questao = getById(questaoId);

        NivelDificuldade nivelDificuldade = new NivelDificuldade();
        nivelDificuldade.setNivelDificuldadeId(dto.getNivelDificuldadeId());

        TipoQuestao tipoQuestao = new TipoQuestao();
        tipoQuestao.setTipoQuestaoId(dto.getTipoQuestaoId());

        questao.setNivelDificuldade(nivelDificuldade);
        questao.setTipoQuestao(tipoQuestao);
        questao.setGabarito(dto.getGabarito());
        questao.setEnunciado(dto.getEnunciado());
    }

    @Transactional
    public void delete(UUID questaoId, List<String> tipoPerfil) {
        if (!tipoPerfil.contains("Professor")) {
            throw new RuntimeException("Usario não contem permisão para essa tarefa");
        }

        getById(questaoId);

        questaoPersonalizadaRepository.deleteById(questaoId);
    }

    @Transactional
    public QuestaoPersonalizada getById(UUID id) {
        return questaoPersonalizadaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Questão Aula não encontrada"));
    }

    @Transactional
    public List<QuestaoPersonalizada> getByAluno(UUID aluno, Integer status, UUID idioma) {
        return questaoPersonalizadaRepository.findByAluno(aluno, status, idioma);
    }

    @Transactional
    public List<QuestaoPersonalizada> corrigirAtividades(List<RespostaQuestaoPersonalizadaDto> listDto, UUID alunoId) {
        List <QuestaoPersonalizada> respostas = new ArrayList<>();
        Double pontos = 0.0;

        Perfil perfil = perfilService.getById(alunoId);
        for (RespostaQuestaoPersonalizadaDto dto : listDto) {
            QuestaoPersonalizada questao = getById(dto.getQuestaoPersonalizadaId());

            questao.setResposta(dto.getResposta());

            Status status = new Status();
            status.setStatusId(3);
            questao.setStatus(status);

            if (dto.getResposta().equals(dto.getGabarito())) {
                pontos += 2;
                questao.setCorreto("S");
            } else {
                pontos -= 2;
                questao.setCorreto("N");
            }

            PerfilIdioma perfilIdioma = perfilIdiomaService.getByPerfilAndIdioma(alunoId, questao.getIdioma().getIdiomaId());

            Double pontosIdioma = 2 + perfilIdioma.getPontosRanking();
            perfilIdioma.setPontosRanking(pontosIdioma);

            respostas.add(questao);
        }

        Double pontosGeral = pontos + perfil.getPontosRanking();
        perfil.setPontosRanking(pontosGeral);

        return respostas;
    }
}
