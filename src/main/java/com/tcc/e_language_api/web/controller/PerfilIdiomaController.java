package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.entity.AlunoProfessor;
import com.tcc.e_language_api.entity.PerfilIdioma;
import com.tcc.e_language_api.service.PerfilIdiomaService;
import com.tcc.e_language_api.web.dto.PerfilIdiomaDto;
import com.tcc.e_language_api.web.dto.mapper.AlunoProfessorMapper;
import com.tcc.e_language_api.web.dto.mapper.PerfilIdiomaMapper;
import com.tcc.e_language_api.web.exception.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/perfil-idioma")
public class PerfilIdiomaController {
    private final PerfilIdiomaService perfilIdiomaService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PerfilIdiomaDto dto, HttpServletRequest request) {
        try {
            perfilIdiomaService.create(PerfilIdiomaMapper.toEntity(dto));
            return ResponseEntity.status(HttpStatus.CREATED).body(new ErrorMessage(request, HttpStatus.CREATED,"Perfil vinculado com idioma com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id, HttpServletRequest request) {
        try{
            perfilIdiomaService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ErrorMessage(request, HttpStatus.OK,"vinculo excluido com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id, HttpServletRequest request) {
        try{
            PerfilIdioma perfilIdioma = perfilIdiomaService.getById(id); ;
            return ResponseEntity.status(HttpStatus.OK).body(PerfilIdiomaMapper.toDto(perfilIdioma));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()));
        }
    }

    @GetMapping("/{perfilId}/perfil")
    public ResponseEntity<?> getAll(@PathVariable UUID perfilId, HttpServletRequest request) {
        try{
            List<PerfilIdioma> perfilIdioma = perfilIdiomaService.getByPerfil(perfilId); ;
            return ResponseEntity.status(HttpStatus.OK).body(PerfilIdiomaMapper.toListDto(perfilIdioma));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()));
        }
    }
}
