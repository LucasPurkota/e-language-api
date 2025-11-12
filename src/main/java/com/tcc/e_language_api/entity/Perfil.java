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
@Table(name = "perfil")
public class Perfil {
    @Id
    @GeneratedValue(generator = "uuid_generate_v4()")
    @Column(name = "perfil_id", nullable = false, unique = true)
    private UUID perfilId;
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false,
            foreignKey = @ForeignKey(name = "perfil_fk",
                    foreignKeyDefinition = "FOREIGN KEY (usuario_id) REFERENCES usuario (usuario_id) ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "tipo_perfil_id", nullable = false,
            foreignKey = @ForeignKey(name = "perfil_fk2",
                    foreignKeyDefinition = "FOREIGN KEY (tipo_perfil_id) REFERENCES tipo_perfil (tipo_perfil_id) ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private TipoPerfil tipoPerfil;
    @Column(name = "pontos_ranking")
    private Double pontosRanking;
    @Transient
    private int posicaoRanking;
}
