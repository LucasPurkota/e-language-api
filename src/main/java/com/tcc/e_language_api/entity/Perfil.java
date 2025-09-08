package com.tcc.e_language_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Perfil")
public class Perfil {
    @Id
    @GeneratedValue(generator = "uuid_generate_v4()")
    @Column(name = "perfil_id", nullable = false, unique = true)
    private UUID perfilId;
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_usuario_perfil",
                    foreignKeyDefinition = "FOREIGN KEY (usuario_id) REFERENCES usuario (usuario_id) ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private Usuario usuario;
    @Column(name = "tipo_perfil", nullable = false)
    private Tipo tipoPerfil;

    public enum Tipo {
        ADMIN, PROFESSOR, ALUNO
    }
}
