package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.*;
import com.tcc.e_language_api.repository.DesafioDiarioRepository;
import com.tcc.e_language_api.repository.QuestaoAulaRepository;
import com.tcc.e_language_api.web.dto.DesafioDiarioDto;
import com.tcc.e_language_api.web.dto.DesafioDiarioRespostaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DesafioDiarioService {
    private final DesafioDiarioRepository desafioDiarioRepository;
    private final QuestaoAulaRepository questaoAulaRepository;
    private final PerfilIdiomaService perfilIdiomaService;
    private final PerfilService perfilService;

    @Transactional
    public void create (UUID perfilIdiomaId) {
        PerfilIdioma perfilIdioma = perfilIdiomaService.getById(perfilIdiomaId);

        List<QuestaoAula> questoes = questaoAulaRepository.findFiveQuestionsByNivelIdioma(perfilIdioma.getNivelIdioma().getNivelIdiomaId());

        for (QuestaoAula questaoAula : questoes) {
            DesafioDiario desafio = new DesafioDiario();

            desafio.setDataDesafio(LocalDate.now());
            desafio.setPerfilIdioma(perfilIdioma);
            desafio.setQuestaoAula(questaoAula);

            Status status = new Status();
            status.setStatusId(1);

            desafio.setStatus(status);

            desafioDiarioRepository.save(desafio);
        }
    }

    @Transactional
    public List<DesafioDiario> getDesafioDoDia(UUID perfilIdiomaId) {
        List<DesafioDiario> desafioDiarios = desafioDiarioRepository.findDesafios(perfilIdiomaId, LocalDate.now());

        if (desafioDiarios.size() == 0) {
            create(perfilIdiomaId);
        }

        return desafioDiarioRepository.findDesafios(perfilIdiomaId, LocalDate.now());
    }

    @Transactional
    public List<DesafioDiarioRespostaDto> corrigir(List<DesafioDiarioRespostaDto> dtoList, UUID perfilIdiomaId, String usuario) {
        Perfil perfil = perfilService.getByUsuarioAndTipoPerfil("Aluno", usuario);
        PerfilIdioma perfilIdioma = perfilIdiomaService.getById(perfilIdiomaId);
        Double pontos = 0.0;
        for (DesafioDiarioRespostaDto resposta : dtoList) {
            DesafioDiario desafioDiario = getById(resposta.getDesafioDiarioId());

            if (resposta.getResposta().equals(desafioDiario.getQuestaoAula().getGabarito())) {
                pontos += 2;
                desafioDiario.setCorreto("S");
                resposta.setCorreto("Sim");
            }else {
                pontos -= 2;
                desafioDiario.setCorreto("N");
                resposta.setCorreto("Não");
            }

            Status status = new Status();
            status.setStatusId(3);
            desafioDiario.setStatus(status);
            desafioDiario.setResposta(resposta.getResposta());
        }

        Double pontosGeral = perfil.getPontosRanking() + pontos;
        Double pontosIdioma = perfilIdioma.getPontosRanking() + pontos;

        perfil.setPontosRanking(pontosGeral);
        perfilIdioma.setPontosRanking(pontosIdioma);

        return dtoList;
    }

    @Transactional
    public DesafioDiario getById(UUID id) {
        return desafioDiarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Desafio diario não encontrado com o id: "+id));
    }
}
