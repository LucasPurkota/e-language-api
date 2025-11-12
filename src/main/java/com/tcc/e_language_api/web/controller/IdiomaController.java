package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.entity.Idioma;
import com.tcc.e_language_api.entity.PerfilIdioma;
import com.tcc.e_language_api.jwt.JwtUserDetails;
import com.tcc.e_language_api.service.IdiomaService;
import com.tcc.e_language_api.web.docs.IdiomaApiDocs;
import com.tcc.e_language_api.web.dto.IdiomaDto;
import com.tcc.e_language_api.web.dto.mapper.IdiomaMapper;
import com.tcc.e_language_api.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("api/v1/idioma")
@Tag(name = "4. Idiomas", description = "Operações relacionadas ao gerenciamento de idiomas")
public class IdiomaController {
    private final IdiomaService idiomaService;
    
    @PostMapping
    @IdiomaApiDocs.CreateIdioma
    public ResponseEntity<?> create(@RequestBody @Valid IdiomaDto dto, @AuthenticationPrincipal JwtUserDetails userDetails, HttpServletRequest request) {
        idiomaService.create(IdiomaMapper.toEntity(dto), userDetails.getRole());
        return ResponseEntity.status(HttpStatus.CREATED).body(new ErrorMessage(request, HttpStatus.CREATED, "idioma criado com sucesso!"));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody @Valid IdiomaDto dto, @AuthenticationPrincipal JwtUserDetails userDetails, HttpServletRequest request){
        try {
            idiomaService.update(id, dto, userDetails.getRole());
            return ResponseEntity.status(HttpStatus.OK).body(new ErrorMessage(request, HttpStatus.OK, "idioma alterado com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id, @AuthenticationPrincipal JwtUserDetails userDetails, HttpServletRequest request){
        try {
            idiomaService.delete(id, userDetails.getRole());
            return ResponseEntity.status(HttpStatus.OK).body(new ErrorMessage(request, HttpStatus.CREATED, "idioma excluido com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id){
        try {
            Idioma idioma = idiomaService.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(IdiomaMapper.toDto(idioma));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    @IdiomaApiDocs.GetAll
    public ResponseEntity<List<IdiomaDto>> getAll() {
        List<Idioma> idiomas = idiomaService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(IdiomaMapper.toListDto(idiomas));
    }

    @GetMapping("/{perfilId}/perfil")
    public ResponseEntity<?> getByPerfil(@PathVariable UUID perfilId){
        try {
            List<Idioma> idiomas = idiomaService.getByPerfil(perfilId);
            return ResponseEntity.status(HttpStatus.OK).body(IdiomaMapper.toListDto(idiomas));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
