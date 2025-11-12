package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.entity.Perfil;
import com.tcc.e_language_api.entity.QuestaoAula;
import com.tcc.e_language_api.service.RankingService;
import com.tcc.e_language_api.web.dto.mapper.PerfilMapper;
import com.tcc.e_language_api.web.dto.mapper.QuestaoAulaMapper;
import com.tcc.e_language_api.web.exception.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/ranking")
public class RankingController {
    private final RankingService rankingService;

    @GetMapping
    public ResponseEntity<?> getAll(HttpServletRequest request) {
        try{
            List<Perfil> perfil = rankingService.getRankigTotal();

            return ResponseEntity.status(HttpStatus.OK).body(PerfilMapper.toListRanking(perfil));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    @GetMapping("/{idioma}/idioma")
    public ResponseEntity<?> getByIdioma(@PathVariable String idioma, HttpServletRequest request) {
        try{
            List<Perfil> perfil = rankingService.getRankigByIdioma(idioma);

            return ResponseEntity.status(HttpStatus.OK).body(PerfilMapper.toListRanking(perfil));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }
}
