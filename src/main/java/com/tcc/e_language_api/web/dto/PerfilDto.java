package com.tcc.e_language_api.web.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PerfilDto {
    private UUID usuarioId;
    private int tipoPerfilId;
}
