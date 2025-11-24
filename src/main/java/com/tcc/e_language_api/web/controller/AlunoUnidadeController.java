package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.entity.AlunoUnidade;
import com.tcc.e_language_api.entity.AlunoUnidadeAula;
import com.tcc.e_language_api.service.AlunoUnidadeAulaService;
import com.tcc.e_language_api.service.AlunoUnidadeService;
import com.tcc.e_language_api.web.dto.mapper.AlunoUnidadeAulaMapper;
import com.tcc.e_language_api.web.dto.mapper.AlunoUnidadeMapper;
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
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/aluno-unidade")
public class AlunoUnidadeController {
    private final AlunoUnidadeService alunoUnidadeService;

    @GetMapping("/{alunoId}/aluno")
    public ResponseEntity<?> getByUnidade(@PathVariable UUID alunoId, HttpServletRequest request){
        try {
            List<AlunoUnidade> unidades = alunoUnidadeService.getByAluno(alunoId);
            return ResponseEntity.status(HttpStatus.CREATED).body(AlunoUnidadeMapper.toListDto(unidades));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, e.getMessage()));
        }
    }
}
