package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.Usuario;
import com.tcc.e_language_api.exception.EntityNotFoundException;
import com.tcc.e_language_api.exception.UsernameUniqueViolationException;
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
    public Usuario create(Usuario usuario) {
        // Verificar se email já existe
        if (existsByEmail(usuario.getEmail())) {
            throw new UsernameUniqueViolationException(String.format("Email '%s' já cadastrado", usuario.getEmail()));
        }
        
        // Verificar se CPF já existe
        if (existsByCpf(usuario.getCpf())) {
            throw new UsernameUniqueViolationException(String.format("CPF '%s' já cadastrado", usuario.getCpf()));
        }
        
        // Criptografar senha
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        
        // Configurar relacionamentos
        if (usuario.getPerfil() != null) {
            usuario.getPerfil().forEach(perfil -> perfil.setUsuario(usuario));
        }
        
        if (usuario.getEnderecos() != null) {
            usuario.getEnderecos().forEach(endereco -> endereco.setUsuario(usuario));
        }
        
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario update(UUID id, Usuario usuarioAtualizado) {
        Usuario usuario = getById(id);
        
        // Atualizar apenas campos não nulos
        if (usuarioAtualizado.getNome() != null) {
            usuario.setNome(usuarioAtualizado.getNome());
        }
        
        if (usuarioAtualizado.getEmail() != null && !usuario.getEmail().equals(usuarioAtualizado.getEmail())) {
            if (existsByEmail(usuarioAtualizado.getEmail())) {
                throw new UsernameUniqueViolationException(String.format("Email '%s' já cadastrado", usuarioAtualizado.getEmail()));
            }
            usuario.setEmail(usuarioAtualizado.getEmail());
        }
        
        if (usuarioAtualizado.getCpf() != null && !usuario.getCpf().equals(usuarioAtualizado.getCpf())) {
            if (existsByCpf(usuarioAtualizado.getCpf())) {
                throw new UsernameUniqueViolationException(String.format("CPF '%s' já cadastrado", usuarioAtualizado.getCpf()));
            }
            usuario.setCpf(usuarioAtualizado.getCpf());
        }
        
        if (usuarioAtualizado.getSenha() != null) {
            usuario.setSenha(passwordEncoder.encode(usuarioAtualizado.getSenha()));
        }
        
        if (usuarioAtualizado.getEnderecos() != null) {
            // Remover endereços antigos
            usuario.getEnderecos().clear();
            // Adicionar novos endereços
            usuarioAtualizado.getEnderecos().forEach(endereco -> {
                endereco.setUsuario(usuario);
                usuario.getEnderecos().add(endereco);
            });
        }
        
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void delete(UUID id) {
        getById(id); // Verificar se existe antes de deletar
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public Usuario getById(UUID id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Usuário com id '%s' não encontrado", id)));
    }

    @Transactional
    public Usuario getByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Usuário com email '%s' não encontrado", email)));
    }

    @Transactional
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }
    
    // ========== MÉTODOS AUXILIARES ==========
    
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }
    
    public boolean existsByCpf(String cpf) {
        return usuarioRepository.existsByCpf(cpf);
    }
    
    // ========== MÉTODOS LEGACY (para compatibilidade) ==========
    
    @Transactional
    public void create(UsuarioDto usuarioDto) {
        usuarioDto.setSenha(passwordEncoder.encode(usuarioDto.getSenha()));
        Usuario usuario = UsuarioMapper.toEntity(usuarioDto, new Usuario());

        if (usuario.getPerfil() != null) {
            usuario.getPerfil().forEach(perfil -> perfil.setUsuario(usuario));
        }

        usuarioRepository.save(usuario);
    }
    
    @Transactional
    public void update(UUID id, UsuarioDto usuarioDto) {
        Usuario usuario = getById(id);
        usuario = UsuarioMapper.toEntity(usuarioDto, usuario);
        usuarioRepository.save(usuario);
    }
}
