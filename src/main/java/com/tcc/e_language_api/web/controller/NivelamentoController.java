package com.tcc.e_language_api.web.controller;

import java.util.List;
import java.util.UUID;

import com.tcc.e_language_api.entity.Nivelamento;
import com.tcc.e_language_api.entity.NivelamentoQuestaoAula;
import com.tcc.e_language_api.entity.RespostaQuestaoAula;
import com.tcc.e_language_api.jwt.JwtUserDetails;
import com.tcc.e_language_api.web.dto.NivelamentoRespostaDto;
import com.tcc.e_language_api.web.dto.RespostaQuestaoAulaDto;
import com.tcc.e_language_api.web.dto.mapper.NivelamentoMapper;
import com.tcc.e_language_api.web.dto.mapper.RespostaQuestaoAulaMapper;
import com.tcc.e_language_api.web.exception.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.tcc.e_language_api.service.NivelamentoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/nivelamento")
public class NivelamentoController {
    private final NivelamentoService nivelamentoService;
    @PostMapping
    public ResponseEntity<String> create(@RequestParam UUID perfilId, @RequestParam String idioma) {
        nivelamentoService.create(idioma, perfilId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Aluno criado com sucesso!");
    }

    @GetMapping("/pendente")
    public ResponseEntity<?> getNivelamentoPendente(@RequestParam UUID perfilId, @RequestParam String idioma) {
        Nivelamento nivelamento = nivelamentoService.getNivelamentoPendente(perfilId, idioma);
        return ResponseEntity.status(HttpStatus.OK).body(NivelamentoMapper.toDto(nivelamento));
    }

    @PutMapping("/{nivelamentoId}/corrigir/{perfilIdiomaId}")
    public ResponseEntity<?> corrigir(@PathVariable UUID nivelamentoId, @PathVariable UUID perfilIdiomaId, @RequestBody List<NivelamentoRespostaDto> dto,
                                                 @AuthenticationPrincipal JwtUserDetails userDetails, HttpServletRequest request) {
        try{
            List<NivelamentoQuestaoAula> respostas = nivelamentoService.corrigir(nivelamentoId, perfilIdiomaId, dto);

            return ResponseEntity.status(HttpStatus.OK).body(RespostaQuestaoAulaMapper.nivelamentoToListDto(respostas));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }
}
