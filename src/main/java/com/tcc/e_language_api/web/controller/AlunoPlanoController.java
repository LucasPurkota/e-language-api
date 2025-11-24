package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.entity.AlunoPlano;
import com.tcc.e_language_api.jwt.JwtUserDetails;
import com.tcc.e_language_api.service.AlunoPlanoService;
import com.tcc.e_language_api.web.dto.AlunoPlanoDto;
import com.tcc.e_language_api.web.dto.mapper.AlunoPlanoMapper;
import com.tcc.e_language_api.web.exception.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/aluno-plano")
public class AlunoPlanoController {
    private final AlunoPlanoService alunoPlanoService;
    @PostMapping
    public ResponseEntity<?> vincularPlano(@RequestBody AlunoPlanoDto dto, HttpServletRequest request) {
        try{
            alunoPlanoService.criarVinculo(AlunoPlanoMapper.toEntity(dto)); ;
            return ResponseEntity.status(HttpStatus.CREATED).body(new ErrorMessage(request, HttpStatus.CREATED, "vinculo incluido com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id, HttpServletRequest request) {
        try{
            alunoPlanoService.deleteVinculo(id); ;
            return ResponseEntity.status(HttpStatus.OK).body(new ErrorMessage(request, HttpStatus.OK, "vinculo excluido com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody AlunoPlanoDto dto, HttpServletRequest request) {
        try{
            alunoPlanoService.update(id, dto); ;
            return ResponseEntity.status(HttpStatus.OK).body(new ErrorMessage(request, HttpStatus.OK, "informações alteradas com sucesso"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    @GetMapping("{alunoId}/aluno")
    public ResponseEntity<?> getByPerfil(@PathVariable UUID alunoId, HttpServletRequest request) {
        try{
            AlunoPlano alunoPlano = alunoPlanoService.getByAluno(alunoId); ;
            return ResponseEntity.status(HttpStatus.OK).body(AlunoPlanoMapper.toDto(alunoPlano));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }
}
