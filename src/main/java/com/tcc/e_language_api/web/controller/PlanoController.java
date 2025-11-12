package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.entity.Plano;
import com.tcc.e_language_api.jwt.JwtUserDetails;
import com.tcc.e_language_api.service.PlanoService;
import com.tcc.e_language_api.web.dto.PlanoDto;
import com.tcc.e_language_api.web.dto.mapper.PlanoMapper;
import com.tcc.e_language_api.web.dto.mapper.QuestaoPersonalizadaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/plano")
public class PlanoController {
    private final PlanoService planoService;

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody PlanoDto dto, @AuthenticationPrincipal JwtUserDetails userDetails) {
        try{
            planoService.update(id, dto, userDetails.getRole()); ;
            return ResponseEntity.status(HttpStatus.OK).body("Plano alterado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody PlanoDto dto, @AuthenticationPrincipal JwtUserDetails userDetails) {
        try{
            planoService.create(PlanoMapper.toEntity(dto), userDetails.getRole()) ;
            return ResponseEntity.status(HttpStatus.CREATED).body("Plano criado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id, @AuthenticationPrincipal JwtUserDetails userDetails) {
        try{
            planoService.delete(id, userDetails.getRole()); ;
            return ResponseEntity.status(HttpStatus.OK).body("Plano deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        try{
            Plano plano = planoService.getById(id); ;
            return ResponseEntity.status(HttpStatus.OK).body(PlanoMapper.toDto(plano));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        try{
            List<Plano> planos = planoService.getAll(); ;
            return ResponseEntity.status(HttpStatus.OK).body(PlanoMapper.toListDto(planos));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
