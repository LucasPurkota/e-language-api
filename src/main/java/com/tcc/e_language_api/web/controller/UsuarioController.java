package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.entity.Usuario;
import com.tcc.e_language_api.service.UsuarioService;
import com.tcc.e_language_api.web.dto.UsuarioDto;
import com.tcc.e_language_api.web.dto.mapper.UsuarioMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController  {
    private final UsuarioService usuarioService;
    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid UsuarioDto usuarioDto) {
        usuarioService.create(usuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Aluno criado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody UsuarioDto usuarioDto) {
        usuarioService.update(id, usuarioDto);
        return ResponseEntity.status(HttpStatus.OK).body("Aluno alterado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        usuarioService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Aluno deletado com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> getById(@PathVariable UUID id) {
        Usuario usuario = usuarioService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(UsuarioMapper.toDto(usuario));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> getAll() {
        List<Usuario> usuario = usuarioService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(UsuarioMapper.toListDto(usuario));
    }
}
