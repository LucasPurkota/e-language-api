package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.entity.AlunoUnidadeAula;
import com.tcc.e_language_api.entity.Aula;
import com.tcc.e_language_api.repository.AlunoUnidadeAulaRepository;
import com.tcc.e_language_api.service.AlunoUnidadeAulaService;
import com.tcc.e_language_api.web.dto.mapper.AlunoUnidadeAulaMapper;
import com.tcc.e_language_api.web.dto.mapper.AulaMapper;
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
@RequestMapping("/api/v1/aluno-unidade-aula")
public class AlunoUnidadeAulaController {
    private final AlunoUnidadeAulaService alunoUnidadeAulaService;

    @GetMapping("/{alunoUnidadeId}/aluno-unidade")
    public ResponseEntity<?> getByUnidade(@PathVariable UUID alunoUnidadeId, HttpServletRequest request){
        try {
            List<AlunoUnidadeAula> aulas = alunoUnidadeAulaService.getByUnidade(alunoUnidadeId);
            return ResponseEntity.status(HttpStatus.CREATED).body(AlunoUnidadeAulaMapper.toListDto(aulas));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }
}
