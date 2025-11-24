package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.Usuario;
import com.tcc.e_language_api.web.dto.UsuarioDto;
import com.tcc.e_language_api.web.dto.usuario.request.UsuarioCreateRequest;
import com.tcc.e_language_api.web.dto.usuario.request.UsuarioUpdateRequest;
import com.tcc.e_language_api.web.dto.usuario.response.UsuarioResponse;
import com.tcc.e_language_api.web.dto.usuario.response.UsuarioListResponse;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {
    
    private static final ModelMapper mapper = new ModelMapper();
    
    // ========== CONVERSÕES PARA ENTIDADE ==========
    
    public static Usuario toEntity(UsuarioCreateRequest dto) {
        if (dto == null) {
            throw new IllegalArgumentException("UsuarioCreateRequest não pode ser nulo");
        }
        
        Usuario entity = mapper.map(dto, Usuario.class);
        configureEntityRelationships(entity, dto.getEnderecos());
        return entity;
    }
    
    public static Usuario toEntity(UsuarioUpdateRequest dto) {
        if (dto == null) {
            throw new IllegalArgumentException("UsuarioUpdateRequest não pode ser nulo");
        }
        
        Usuario entity = mapper.map(dto, Usuario.class);
        configureEntityRelationships(entity, dto.getEnderecos());
        return entity;
    }
    
    /**
     * Configura os relacionamentos bidirecionais da entidade Usuario.
     * Este método é essencial para a consistência dos dados no ORM (Hibernate)
     * e garante que as relações sejam adequadamente persistidas no banco.
     * 
     * @param entity Usuario entity to configure
     * @param enderecosDto List of address DTOs from the request
     */
    private static void configureEntityRelationships(Usuario entity, 
                                                   List<com.tcc.e_language_api.web.dto.endereco.request.EnderecoCreateRequest> enderecosDto) {
        // Configurar endereços com relacionamento bidirecional
        if (enderecosDto != null && !enderecosDto.isEmpty()) {
            entity.setEnderecos(EnderecoMapper.toEntityList(enderecosDto));
            
            // Garantir relacionamento bidirecional para consistência do ORM
            entity.getEnderecos().forEach(endereco -> {
                if (endereco != null) {
                    endereco.setUsuario(entity);
                }
            });
        } else {
            // Inicializar lista vazia para evitar NPE no frontend
            entity.setEnderecos(new java.util.ArrayList<>());
        }
    }
    
    public static Usuario toEntity(UsuarioDto dto) {
        return mapper.map(dto, Usuario.class);
    }
    
    // ========== CONVERSÕES PARA DTO RESPONSE ==========
    
    public static UsuarioResponse toResponse(Usuario entity) {
        if (entity == null) {
            return null;
        }
        
        UsuarioResponse response = mapper.map(entity, UsuarioResponse.class);
        response.setCriadoEm(entity.getDataCriacao());
        
        // Configurar endereços com verificação defensiva para Angular
        response.setEnderecos(
            entity.getEnderecos() != null 
                ? EnderecoMapper.toResponseList(entity.getEnderecos())
                : new java.util.ArrayList<>()
        );
        
        // Configurar perfis com verificação defensiva para Angular
        response.setPerfil(
            entity.getPerfil() != null 
                ? PerfilMapper.toResponseList(entity.getPerfil())
                : new java.util.ArrayList<>()
        );
        
        return response;
    }
    
    public static UsuarioListResponse toListResponse(Usuario entity) {
        if (entity == null) {
            return null;
        }
        
        UsuarioListResponse response = mapper.map(entity, UsuarioListResponse.class);
        
        // Definir perfil principal com fallback para Angular
        if (entity.getPerfil() != null && !entity.getPerfil().isEmpty()) {
            response.setPerfilPrincipal(
                entity.getPerfil().get(0).getTipoPerfil().getDescricao()
            );
        } else {
            response.setPerfilPrincipal("INDEFINIDO");
        }
        
        // Definir status com valor padrão consistente para Angular
        response.setStatus("ATIVO");
        
        return response;
    }
    
    // ========== CONVERSÕES PARA LISTA ==========
    
    public static List<UsuarioResponse> toResponseList(List<Usuario> entities) {
        if (entities == null) {
            return new java.util.ArrayList<>();
        }
        
        return entities.stream()
            .filter(entity -> entity != null) // Filtrar nulls para Angular
            .map(UsuarioMapper::toResponse)
            .collect(Collectors.toList());
    }
    
    public static List<UsuarioListResponse> toListResponseList(List<Usuario> entities) {
        if (entities == null) {
            return new java.util.ArrayList<>();
        }
        
        return entities.stream()
            .filter(entity -> entity != null) // Filtrar nulls para Angular
            .map(UsuarioMapper::toListResponse)
            .collect(Collectors.toList());
    }
    
    // ========== MÉTODOS LEGACY (para compatibilidade) ==========
    
    public static UsuarioDto toDto(Usuario entity) {
        return mapper.map(entity, UsuarioDto.class);
    }
    
    public static List<UsuarioDto> toListDto(List<Usuario> entities) {
        if (entities == null) {
            return new java.util.ArrayList<>();
        }
        
        return entities.stream()
            .filter(entity -> entity != null)
            .map(entity -> mapper.map(entity, UsuarioDto.class))
            .collect(Collectors.toList());
    }
    
    public static Usuario toEntity(UsuarioDto usuarioDto, Usuario usuario) {
        if (usuarioDto != null && usuario != null) {
            usuarioDto.setUsuarioId(usuario.getUsuarioId());
            mapper.map(usuarioDto, usuario);
        }
        return usuario;
    }
    
    // ========== MÉTODOS UTILITÁRIOS PARA ANGULAR ==========
    
    /**
     * Converte uma lista de entidades para o formato simplificado usado em dropdowns
     * e componentes de seleção no Angular.
     * 
     * @param entities Lista de entidades Usuario
     * @return Lista com ID e nome para componentes select do Angular
     */
    public static List<java.util.Map<String, Object>> toSelectOptions(List<Usuario> entities) {
        if (entities == null) {
            return new java.util.ArrayList<>();
        }
        
        return entities.stream()
            .filter(entity -> entity != null && entity.getUsuarioId() != null && entity.getNome() != null)
            .map(entity -> {
                java.util.Map<String, Object> option = new java.util.HashMap<>();
                option.put("value", entity.getUsuarioId().toString());
                option.put("label", entity.getNome());
                option.put("email", entity.getEmail());
                return option;
            })
            .collect(Collectors.toList());
    }
    
    /**
     * Cria um UsuarioResponse mínimo para casos onde apenas informações básicas
     * são necessárias (ex: dados do usuário logado no header do Angular).
     * 
     * @param entity Entidade Usuario
     * @return UsuarioResponse com apenas dados essenciais
     */
    public static java.util.Map<String, Object> toMinimalResponse(Usuario entity) {
        java.util.Map<String, Object> response = new java.util.HashMap<>();
        
        if (entity != null) {
            response.put("usuarioId", entity.getUsuarioId());
            response.put("nome", entity.getNome());
            response.put("email", entity.getEmail());
            
            // Perfil principal para exibir badge no Angular
            if (entity.getPerfil() != null && !entity.getPerfil().isEmpty()) {
                response.put("perfilPrincipal", entity.getPerfil().get(0).getTipoPerfil().getDescricao());
            } else {
                response.put("perfilPrincipal", "INDEFINIDO");
            }
            
            response.put("status", "ATIVO");
        }
        
        return response;
    }
}
