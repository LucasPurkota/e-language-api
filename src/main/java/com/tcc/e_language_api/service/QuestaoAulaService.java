package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.*;
import com.tcc.e_language_api.repository.QuestaoAulaRepository;
import com.tcc.e_language_api.repository.RespostaQuestaoAulaRepository;
import com.tcc.e_language_api.web.dto.QuestaoAulaDto;
import com.tcc.e_language_api.web.dto.RespostaQuestaoAulaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class QuestaoAulaService {
    private final QuestaoAulaRepository questaoAulaRepository;
    private final RespostaQuestaoAulaRepository respostaQuestaoAulaRepository;
    private final PerfilService perfilService;
    private final AlunoUnidadeService alunoUnidadeService;
    private final AlunoUnidadeAulaService alunoUnidadeAulaService;
    private final UnidadeService unidadeService;
    private final PerfilIdiomaService perfilIdiomaService;

    @Transactional
    public void create(QuestaoAula questaoAula, List<String> tipoPerfil){
        if (!tipoPerfil.contains("Admin")) {
            throw new RuntimeException("Usario não contem permisão para essa tarefa");
        }

        questaoAulaRepository.save(questaoAula);
    }

    @Transactional
    public void update(UUID questaoAulaId, QuestaoAulaDto dto, List<String> tipoPerfil) {
        if (!tipoPerfil.contains("Admin")) {
            throw new RuntimeException("Usario não contem permisão para essa tarefa");
        }

        QuestaoAula questaoAula = getById(questaoAulaId);

        NivelDificuldade nivelDificuldade = new NivelDificuldade();
        nivelDificuldade.setNivelDificuldadeId(dto.getNivelDificuldadeId());

        TipoQuestao tipoQuestao = new TipoQuestao();
        tipoQuestao.setTipoQuestaoId(dto.getTipoQuestaoId());

        questaoAula.setNivelDificuldade(nivelDificuldade);
        questaoAula.setTipoQuestao(tipoQuestao);
        questaoAula.setGabarito(dto.getGabarito());
        questaoAula.setEnunciado(dto.getEnunciado());
    }

    @Transactional
    public void delete(UUID questaoAulaId, List<String> tipoPerfil) {
        if (!tipoPerfil.contains("Admin")) {
            throw new RuntimeException("Usario não contem permisão para essa tarefa");
        }

        getById(questaoAulaId);

        questaoAulaRepository.deleteById(questaoAulaId);
    }

    @Transactional
    public QuestaoAula getById(UUID id) {
        return questaoAulaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Questão Aula não encontrada"));
    }

    @Transactional
    public List<QuestaoAula> getByAula(UUID aulaId) {
        return questaoAulaRepository.findByAulaAulaId(aulaId);
    }

    @Transactional
    public List<QuestaoAula> getAll() {
        return questaoAulaRepository.findAll();
    }
    
    @Transactional
    public List<RespostaQuestaoAula> corrigirAtividades(List<RespostaQuestaoAulaDto> listDto, UUID unidadeId, String usuario) {
        List <RespostaQuestaoAula> listRespQuestAula = new ArrayList<>();
        Double pontos = 0.0;

        Perfil perfil = perfilService.getByUsuarioAndTipoPerfil("Aluno", usuario);
        AlunoUnidade alunoUnidade = alunoUnidadeService.getByAlunoAndUnidade(perfil.getPerfilId(), unidadeId);
        for (RespostaQuestaoAulaDto dto : listDto) {
            RespostaQuestaoAula respQuestAula = new RespostaQuestaoAula();
            QuestaoAula questaoAula = new QuestaoAula();
            questaoAula.setQuestaoAulaId(dto.getQuestaoAulaId());
            respQuestAula.setQuestaoAula(questaoAula);
            respQuestAula.setAlunoUnidade(alunoUnidade);
            respQuestAula.setResposta(dto.getResposta());
            if (dto.getResposta().equals(dto.getGabarito())) {
                pontos += 2;
                respQuestAula.setCorreto("S");
            } else {
                pontos -= 2;
                respQuestAula.setCorreto("N");
            }
            respostaQuestaoAulaRepository.save(respQuestAula);
            listRespQuestAula.add(respQuestAula);
        }

        Double pontosGeral = pontos + perfil.getPontosRanking();
        perfil.setPontosRanking(pontosGeral);

        Unidade unidade = unidadeService.getById(unidadeId);

        PerfilIdioma perfilIdioma = perfilIdiomaService.getByPerfilAndIdioma(perfil.getPerfilId(), unidade.getIdioma().getIdiomaId());

        Double pontosIdioma = pontos + perfilIdioma.getPontosRanking();
        perfilIdioma.setPontosRanking(pontosIdioma);

        return listRespQuestAula;
    }
}
