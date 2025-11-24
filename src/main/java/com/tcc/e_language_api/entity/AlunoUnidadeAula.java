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
@Table(name = "aluno_unidade_aula")
public class AlunoUnidadeAula {
    @Id
    @GeneratedValue(generator = "uuid_generate_v4()")
    @Column(name = "aluno_unidade_aula_id", nullable = false, unique = true)
    private UUID alunoUnidadeAulaId;
    @ManyToOne
    @JoinColumn(name = "aluno_unidade_id", nullable = false,
            foreignKey = @ForeignKey(name = "aluno_unidade_aula_fk",
                    foreignKeyDefinition = "FOREIGN KEY (aluno_unidade_id) REFERENCES aluno_unidade (aluno_unidade_id) " +
                            "ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private AlunoUnidade alunoUnidade;
    @ManyToOne
    @JoinColumn(name = "aula_id", nullable = false,
            foreignKey = @ForeignKey(name = "aluno_unidade_aula_fk2",
                    foreignKeyDefinition = "FOREIGN KEY (aula_id) REFERENCES aula (aula_id) " +
                            "ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private Aula aula;
    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false,
            foreignKey = @ForeignKey(name = "aluno_unidade_aula_fk3",
                    foreignKeyDefinition = "FOREIGN KEY (status_id) REFERENCES status (status_id) " +
                            "ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private Status status;
}
