package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.entity.Unidade;
import com.tcc.e_language_api.jwt.JwtUserDetails;
import com.tcc.e_language_api.service.UnidadeService;
import com.tcc.e_language_api.web.docs.UnidadeApiDocs;
import com.tcc.e_language_api.web.dto.UnidadeDto;
import com.tcc.e_language_api.web.dto.mapper.UnidadeMapper;
import com.tcc.e_language_api.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/unidade")
@Tag(name = "4. Unidades", description = "Operações relacionadas ao gerenciamento de unidades")
public class UnidadeController {

    @Autowired
    private UnidadeService unidadeService;

    @PostMapping
    @UnidadeApiDocs.CreateUnidade
    public ResponseEntity<?> create(@RequestBody UnidadeDto unidadeDto, @AuthenticationPrincipal JwtUserDetails userDetails, HttpServletRequest request) {
        try {
            unidadeService.create(UnidadeMapper.toEntity(unidadeDto), userDetails.getRole());
            return ResponseEntity.status(HttpStatus.CREATED).body(new ErrorMessage(request, HttpStatus.CREATED, "Unidade criada com sucesso!"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(request, HttpStatus.BAD_REQUEST,e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody UnidadeDto dto, @AuthenticationPrincipal JwtUserDetails userDetails, HttpServletRequest request) {
        try {
            unidadeService.update(id, dto, userDetails.getRole());
            return ResponseEntity.ok(new ErrorMessage(request, HttpStatus.OK, "Unidade alterada com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id, @AuthenticationPrincipal JwtUserDetails userDetails, HttpServletRequest request) {
        try {
            unidadeService.delete(id, userDetails.getRole());
            return ResponseEntity.ok(new ErrorMessage(request, HttpStatus.OK, "Unidade deletada com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    @UnidadeApiDocs.GetById
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<?> getById(@Parameter(description = "ID da unidade") @PathVariable UUID id, HttpServletRequest request) {
        try {
            Unidade unidade = unidadeService.getById(id);
            return ResponseEntity.ok(UnidadeMapper.toDto(unidade));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()));
        }
    }

    @GetMapping("/{idiomaId}/idioma")
    @UnidadeApiDocs.GetByIdiomaId
    public ResponseEntity<?> getUnidadesByIdiomaId(@Parameter(description = "ID do idioma") @PathVariable UUID idiomaId, HttpServletRequest request) {
        try {
            var unidades = unidadeService.getByIdiomaId(idiomaId);
            var unidadesDto = unidades.stream()
                    .map(UnidadeMapper::toDto)
                    .toList();
            return ResponseEntity.ok(unidadesDto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor");
        }
    }

    @GetMapping
    @UnidadeApiDocs.GetAll
    public ResponseEntity<?> getAllUnidades() {
        try {
            var unidades = unidadeService.getAll();
            var unidadesDto = unidades.stream()
                    .map(UnidadeMapper::toDto)
                    .toList();
            return ResponseEntity.ok(unidadesDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor");
        }
    }
}
