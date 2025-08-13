package com.tcc.e_language_api.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tcc.e_language_api.service.UsuarioService;
import com.tcc.e_language_api.web.dto.UsuarioDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
  private final UsuarioService usuarioService;
  
  @PostMapping
  public ResponseEntity<String> getUsuario(@RequestBody UsuarioDto usuarioDTO) {
    usuarioService.create(usuarioDTO);
    return new ResponseEntity<>("Usuário criado com sucesso!", HttpStatus.CREATED);
  }
}
