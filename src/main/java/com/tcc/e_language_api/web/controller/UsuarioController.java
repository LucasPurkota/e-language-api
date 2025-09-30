package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.entity.Usuario;
import com.tcc.e_language_api.service.UsuarioService;
import com.tcc.e_language_api.web.dto.UsuarioDto;
import com.tcc.e_language_api.web.dto.mapper.UsuarioMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @Operation(summary = "Criar novo usuário", 
               description = "Cria um novo usuário no sistema com os dados fornecidos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @RequestBody(
        description = "Dados do usuário a ser criado",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = UsuarioDto.class),
            examples = {
                @ExampleObject(
                    name = "Usuário Básico",
                    summary = "Exemplo básico de usuário",
                    description = "Criação de usuário com apenas os campos obrigatórios",
                    value = """
                            {
                            "nome": "Maria Santos",
                            "cpf": "71756301077",
                            "email": "maria.santos@email.com",
                            "senha": "SenhaForte@456",
                            "enderecos": [
                                {
                                "tipo": "RESIDENCIAL",
                                "cep": "01234-567",
                                "logradouro": "Rua das Flores",
                                "bairro": "Centro",
                                "cidade": "São Paulo",
                                "uf": "SP",
                                "pais": "Brasil",
                                "complemento": "Apto 101",
                                "numero": 123
                                }
                            ]
                            }
                        """
                )
            }
        )
    )
    public ResponseEntity<String> create(@org.springframework.web.bind.annotation.RequestBody @Valid UsuarioDto usuarioDto) {
        usuarioService.create(usuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Aluno criado com sucesso!");
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuário", 
               description = "Atualiza os dados de um usuário existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
        @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<String> update(@Parameter(description = "ID do usuário") @PathVariable UUID id, 
                                       @RequestBody UsuarioDto usuarioDto) {
        usuarioService.update(id, usuarioDto);
        return ResponseEntity.status(HttpStatus.OK).body("Aluno alterado com sucesso!");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar usuário", 
               description = "Remove um usuário do sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
        @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<String> delete(@Parameter(description = "ID do usuário") @PathVariable UUID id) {
        usuarioService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Aluno deletado com sucesso!");
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID", 
               description = "Retorna os dados de um usuário específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado", 
                    content = @Content(schema = @Schema(implementation = UsuarioDto.class))),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
        @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<UsuarioDto> getById(@Parameter(description = "ID do usuário") @PathVariable UUID id) {
        Usuario usuario = usuarioService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(UsuarioMapper.toDto(usuario));
    }

    @GetMapping
    @Operation(summary = "Listar todos os usuários", 
               description = "Retorna uma lista com todos os usuários cadastrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso"),
        @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<UsuarioDto>> getAll() {
        List<Usuario> usuario = usuarioService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(UsuarioMapper.toListDto(usuario));
    }
}
