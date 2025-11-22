package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.entity.Avaliacao;
import com.tcc.e_language_api.entity.AvaliacaoQuestaoAula;
import com.tcc.e_language_api.jwt.JwtUserDetails;
import com.tcc.e_language_api.service.AvaliacaoService;
import com.tcc.e_language_api.web.dto.AvaliacaoDto;
import com.tcc.e_language_api.web.dto.AvaliacaoQuestaoAulaDto;
import com.tcc.e_language_api.web.dto.mapper.AvaliacaoMapper;
import com.tcc.e_language_api.web.dto.mapper.QuestaoPersonalizadaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/avaliacao")
@RequiredArgsConstructor
public class AvaliacaoController {
    private final AvaliacaoService avaliacaoService;

    @PostMapping
    public ResponseEntity<String> create(@RequestParam UUID unidadeId, @AuthenticationPrincipal JwtUserDetails userDetails){
        try{
            avaliacaoService.create(unidadeId, userDetails.getUsername());
            return ResponseEntity.status(HttpStatus.CREATED).body("Avaliação criada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{unidadeId}/unidade")
    public ResponseEntity<?> getByUnidade(@PathVariable UUID unidadeId, @AuthenticationPrincipal JwtUserDetails userDetails){
        try{
            List<Avaliacao> avaliacoes = avaliacaoService.getByUnidade(unidadeId, userDetails.getUsername());
            return ResponseEntity.status(HttpStatus.CREATED).body(AvaliacaoMapper.toListDto(avaliacoes));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/pendente/{unidadeId}/unidade")
    public ResponseEntity<?> getPendente(@PathVariable UUID unidadeId, @AuthenticationPrincipal JwtUserDetails userDetails){
        try{
            Avaliacao avaliacao = avaliacaoService.getPendente(unidadeId, userDetails.getUsername());
            return ResponseEntity.status(HttpStatus.OK).body(AvaliacaoMapper.toDto(avaliacao));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{avaliacaoId}/corrigir/{unidadeId}")
    public ResponseEntity<?> corrigir(@PathVariable UUID avaliacaoId, @PathVariable UUID unidadeId, @RequestBody List<AvaliacaoQuestaoAulaDto> respostas, @AuthenticationPrincipal JwtUserDetails userDetails){
        try{
            List<AvaliacaoQuestaoAula> correcao = avaliacaoService.corrigir(avaliacaoId, unidadeId, userDetails.getUsername(), respostas);
            return ResponseEntity.status(HttpStatus.OK).body(AvaliacaoMapper.toListDtoResposta(correcao));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
