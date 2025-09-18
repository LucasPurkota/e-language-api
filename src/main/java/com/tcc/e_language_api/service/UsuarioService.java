package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.Usuario;
import com.tcc.e_language_api.repository.UsuarioRepository;
import com.tcc.e_language_api.web.dto.UsuarioDto;
import com.tcc.e_language_api.web.dto.mapper.UsuarioMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UsuarioService {
  private final UsuarioRepository usuarioRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public void create(UsuarioDto usuarioDto) {
    try {
//      Usuario usuario = getByEmail(usuarioDto.getEmail());
//      if (usuario.getUsuarioId() != null) {
//        throw new RuntimeException("E-mail ja cadastrado");
//      }

      usuarioDto.setSenha(passwordEncoder.encode(usuarioDto.getSenha()));

      usuarioRepository.save(UsuarioMapper.toEntity(usuarioDto, new Usuario()));

    } catch (Exception e) {
      throw new RuntimeException("Error creating Usuario: " + e.getMessage());
    }
  }

  @Transactional
  public void update(UUID id, UsuarioDto usuarioDto) {
    try {
      Usuario usuario = getById(id);

      usuario = UsuarioMapper.toEntity(usuarioDto, usuario);
    } catch (Exception e) {
      throw new RuntimeException("Error creating Usuario: " + e.getMessage());
    }
  }


  @Transactional
  public void delete(UUID id) {
    try {
      // getById(id);

      usuarioRepository.deleteById(id);

    } catch (Exception e) {
      throw new RuntimeException("Error creating Usuario: " + e.getMessage());
    }
  }

  @Transactional
  public Usuario getById(UUID id) {
      return usuarioRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("Usuario not found"));
  }

  @Transactional
  public Usuario getByEmail(String email) {
      return usuarioRepository.findByEmail(email)
              .orElseThrow(() -> new RuntimeException("Usuario not found"));
  }

  @Transactional
  public List<Usuario> getAll() {
      return usuarioRepository.findAll();
  }
}
