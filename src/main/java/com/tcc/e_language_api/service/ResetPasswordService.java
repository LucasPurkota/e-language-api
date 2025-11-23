package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.PasswordResetToken;
import com.tcc.e_language_api.entity.Usuario;
import com.tcc.e_language_api.repository.PasswordResetTokenRepository;
import com.tcc.e_language_api.repository.UsuarioRepository;
import com.tcc.e_language_api.web.dto.ResetPasswordResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class ResetPasswordService {

    private static final Logger logger = LoggerFactory.getLogger(ResetPasswordService.class);
    private static final long TOKEN_EXPIRY_MINUTES = 15;

    private final UsuarioRepository userRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    public ResetPasswordService(UsuarioRepository userRepository,
                                 PasswordResetTokenRepository tokenRepository,
                                 PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
        logger.info("✅ ResetPasswordService inicializado (verificação por CPF)");
    }

    /**
     * Etapa 1: Verifica email + primeiros 3 dígitos do CPF e gera token temporário
     */
    public ResetPasswordResponse verifyAndGenerateToken(String email, String cpfDigits) {
        logger.info("🔍 Verificando identidade para email: {}", email);
        
        Usuario user = userRepository.findByEmail(email)
            .orElseThrow(() -> {
                logger.warn("⚠️ Email não encontrado: {}", email);
                return new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email ou CPF inválidos");
            });

        // Verifica se CPF começa com os dígitos fornecidos
        String userCpf = user.getCpf();
        if (userCpf == null || !userCpf.startsWith(cpfDigits)) {
            logger.warn("⚠️ CPF não corresponde para email: {}", email);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email ou CPF inválidos");
        }

        // Gera token temporário (válido por 15 minutos)
        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken(token, user, TOKEN_EXPIRY_MINUTES / 60.0);
        tokenRepository.save(resetToken);

        logger.info("✅ Token gerado para {}: {} (expira em {} min)", email, token, TOKEN_EXPIRY_MINUTES);
        
        return new ResetPasswordResponse(
            token, 
            "Verificação bem-sucedida. Use o token para definir nova senha."
        );
    }

    /**
     * Etapa 2: Valida token e redefine senha
     */
    public void resetPassword(String token, String newPassword) {
        logger.info("🔐 Tentando resetar senha com token: {}", token);
        
        PasswordResetToken resetToken = tokenRepository.findByToken(token)
            .orElseThrow(() -> {
                logger.warn("⚠️ Token não encontrado: {}", token);
                return new ResponseStatusException(HttpStatus.NOT_FOUND, "Token inválido");
            });

        // Verifica se token expirou
        if (resetToken.getExpiryDate().isBefore(Instant.now())) {
            logger.warn("⚠️ Token expirado: {}", token);
            tokenRepository.delete(resetToken);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token expirado");
        }

        // Atualiza senha do usuário
        Usuario user = resetToken.getUser();
        user.setSenha(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        // Remove token usado
        tokenRepository.delete(resetToken);
        
        logger.info("✅ Senha resetada com sucesso para usuário: {}", user.getEmail());
    }
}




