package com.tcc.e_language_api.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ChatDto {
    private UUID chatId;
    private UUID alunoProfessorId;
    private UUID remetenteId;
    private String mensagem;
    private LocalDateTime dataEnvio;
}
