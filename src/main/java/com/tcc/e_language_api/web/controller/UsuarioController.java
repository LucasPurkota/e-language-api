package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.entity.Usuario;
import com.tcc.e_language_api.service.UsuarioService;
import com.tcc.e_language_api.web.docs.UsuarioApiDocs;
import com.tcc.e_language_api.web.dto.usuario.request.UsuarioCreateRequest;
import com.tcc.e_language_api.web.dto.usuario.request.UsuarioUpdateRequest;
import com.tcc.e_language_api.web.dto.usuario.response.UsuarioResponse;
import com.tcc.e_language_api.web.dto.usuario.response.UsuarioListResponse;
import com.tcc.e_language_api.web.dto.mapper.UsuarioMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
@Tag(name = "1. Usuários", description = "Operações relacionadas ao gerenciamento de usuários")
public class UsuarioController {
    
    private final UsuarioService usuarioService;
    
    @PostMapping
    @UsuarioApiDocs.CreateUsuario
    public ResponseEntity<UsuarioResponse> create(@RequestBody @Valid UsuarioCreateRequest request) {
        Usuario entity = UsuarioMapper.toEntity(request);
        Usuario savedEntity = usuarioService.create(entity);
        UsuarioResponse response = UsuarioMapper.toResponse(savedEntity);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @UsuarioApiDocs.UpdateUsuario
    public ResponseEntity<UsuarioResponse> update(
            @Parameter(description = "ID do usuário") @PathVariable UUID id,
            @RequestBody @Valid UsuarioUpdateRequest request) {
        
        Usuario entity = UsuarioMapper.toEntity(request);
        Usuario updatedEntity = usuarioService.update(id, entity);
        UsuarioResponse response = UsuarioMapper.toResponse(updatedEntity);
        
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @UsuarioApiDocs.DeleteUsuario
    public ResponseEntity<Void> delete(@Parameter(description = "ID do usuário") @PathVariable UUID id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @UsuarioApiDocs.GetByIdUsuario
    public ResponseEntity<UsuarioResponse> getById(@Parameter(description = "ID do usuário") @PathVariable UUID id) {
        Usuario entity = usuarioService.getById(id);
        UsuarioResponse response = UsuarioMapper.toResponse(entity);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @UsuarioApiDocs.GetAllUsuarios
    public ResponseEntity<List<UsuarioListResponse>> getAll(
            @PageableDefault(size = 10, sort = "nome") Pageable pageable,
            @RequestParam(required = false) String search) {
        
        List<Usuario> entities = usuarioService.getAll(); // TODO: Implementar paginação no service
        List<UsuarioListResponse> response = UsuarioMapper.toListResponseList(entities);
        
        return ResponseEntity.ok(response);
    }
    
    // ========== ENDPOINTS ADICIONAIS ==========
    
    @GetMapping("/me")
    @UsuarioApiDocs.GetCurrentUser
    public ResponseEntity<UsuarioResponse> getCurrentUser() {
        // TODO: Implementar busca do usuário logado
        // UUID currentUserId = authService.getCurrentUserId();
        // Usuario entity = usuarioService.getById(currentUserId);
        // return ResponseEntity.ok(UsuarioMapper.toResponse(entity));
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
    
    @PatchMapping("/{id}/status")
    @UsuarioApiDocs.UpdateUserStatus
    public ResponseEntity<Void> updateStatus(
            @Parameter(description = "ID do usuário") @PathVariable UUID id,
            @RequestParam String status) {
        
        // TODO: Implementar atualização de status
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
