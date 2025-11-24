package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.entity.DesafioDiario;
import com.tcc.e_language_api.jwt.JwtUserDetails;
import com.tcc.e_language_api.service.DesafioDiarioService;
import com.tcc.e_language_api.web.dto.DesafioDiarioRespostaDto;
import com.tcc.e_language_api.web.dto.IdiomaDto;
import com.tcc.e_language_api.web.dto.mapper.DesafioDiarioMapper;
import com.tcc.e_language_api.web.exception.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/desafio-diario")
public class DesafioDiarioController {
    private final DesafioDiarioService desafioDiarioService;

    @PostMapping("/{perfilIdiomaId}/perfil-idioma")
    public ResponseEntity<?> getDesafios(@PathVariable UUID perfilIdiomaId, @AuthenticationPrincipal JwtUserDetails userDetails, HttpServletRequest request){
        try {
            List<DesafioDiario> desafios = desafioDiarioService.getDesafioDoDia(perfilIdiomaId);
            return ResponseEntity.status(HttpStatus.OK).body(DesafioDiarioMapper.toListDto(desafios));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    @PutMapping("corrigir/{perfilIdiomaId}/perfil-idioma")
    public ResponseEntity<?> corrigir(@PathVariable UUID perfilIdiomaId, @RequestBody List<DesafioDiarioRespostaDto> respostas, @AuthenticationPrincipal JwtUserDetails userDetails, HttpServletRequest request){
        try {
            respostas = desafioDiarioService.corrigir(respostas,perfilIdiomaId,userDetails.getUsername());
            return ResponseEntity.status(HttpStatus.OK).body(respostas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }
}
