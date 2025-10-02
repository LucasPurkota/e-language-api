package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.entity.Usuario;
import com.tcc.e_language_api.service.UsuarioService;
import com.tcc.e_language_api.web.docs.UsuarioApiDocs;
import com.tcc.e_language_api.web.dto.UsuarioDto;
import com.tcc.e_language_api.web.dto.mapper.UsuarioMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "1. Usuários", description = "Operações relacionadas ao gerenciamento de usuários")
public class UsuarioController  {
    private final UsuarioService usuarioService;
    
    @PostMapping
    @UsuarioApiDocs.CreateUsuario
    public ResponseEntity<String> create(@org.springframework.web.bind.annotation.RequestBody @Valid UsuarioDto usuarioDto) {
        usuarioService.create(usuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Aluno criado com sucesso!");
    }

    @PutMapping("/{id}")
    @UsuarioApiDocs.UpdateUsuario
    public ResponseEntity<String> update(@Parameter(description = "ID do usuário") @PathVariable UUID id, 
                                       @RequestBody UsuarioDto usuarioDto) {
        usuarioService.update(id, usuarioDto);
        return ResponseEntity.status(HttpStatus.OK).body("Aluno alterado com sucesso!");
    }

    @DeleteMapping("/{id}")
    @UsuarioApiDocs.DeleteUsuario
    public ResponseEntity<String> delete(@Parameter(description = "ID do usuário") @PathVariable UUID id) {
        usuarioService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Aluno deletado com sucesso!");
    }

    @GetMapping("/{id}")
    @UsuarioApiDocs.GetByIdUsuario
    public ResponseEntity<UsuarioDto> getById(@Parameter(description = "ID do usuário") @PathVariable UUID id) {
        Usuario usuario = usuarioService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(UsuarioMapper.toDto(usuario));
    }

    @GetMapping
    @UsuarioApiDocs.GetAllUsuarios
    public ResponseEntity<List<UsuarioDto>> getAll() {
        List<Usuario> usuario = usuarioService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(UsuarioMapper.toListDto(usuario));
    }
}
