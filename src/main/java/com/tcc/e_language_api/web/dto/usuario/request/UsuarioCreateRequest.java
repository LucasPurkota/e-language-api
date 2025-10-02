package com.tcc.e_language_api.web.dto.usuario.request;

import com.tcc.e_language_api.web.dto.endereco.request.EnderecoCreateRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados para criação de um novo usuário")
public class UsuarioCreateRequest {
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    @Schema(description = "Nome completo do usuário", example = "João Silva")
    private String nome;
    
    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF deve estar no formato 000.000.000-00")
    @Schema(description = "CPF do usuário", example = "123.456.789-00")
    private String cpf;
    
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ter um formato válido")
    @Schema(description = "Email do usuário", example = "joao.silva@email.com")
    private String email;
    
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, max = 20, message = "Senha deve ter entre 6 e 20 caracteres")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", 
             message = "Senha deve conter pelo menos uma letra minúscula, uma maiúscula e um número")
    @Schema(description = "Senha do usuário", example = "MinhaSenh@123")
    private String senha;
    
    @Valid
    @Schema(description = "Lista de endereços do usuário")
    private List<EnderecoCreateRequest> enderecos;
}