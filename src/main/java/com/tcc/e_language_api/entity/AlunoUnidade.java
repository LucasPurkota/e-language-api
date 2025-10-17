package com.tcc.e_language_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "aluno_unidade")
public class AlunoUnidade {
    @Id
    @GeneratedValue(generator = "uuid_generate_v4()")
    @Column(name = "aluno_unidade_id", nullable = false, unique = true)
    private UUID alunoUnidadeId;
    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false,
            foreignKey = @ForeignKey(name = "aluno_unidade_fk",
                    foreignKeyDefinition = "FOREIGN KEY (aluno_id ) REFERENCES perfil (perfil_id) " +
                            "ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private Perfil aluno;
    @ManyToOne
    @JoinColumn(name = "unidade_id", nullable = false,
            foreignKey = @ForeignKey(name = "aluno_unidade_fk2",
                    foreignKeyDefinition = "FOREIGN KEY (unidade_id) REFERENCES unidade (unidade_id) " +
                            "ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private Unidade unidade;
}
