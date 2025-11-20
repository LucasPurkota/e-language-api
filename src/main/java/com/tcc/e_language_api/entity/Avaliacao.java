package com.tcc.e_language_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "avaliacao")
public class Avaliacao {
    @Id
    @GeneratedValue(generator = "uuid_generate_v4()")
    @Column(name = "avaliacao_id", nullable = false, unique = true)
    private UUID avaliacaoId;
    @ManyToOne
    @JoinColumn(name = "aluno_unidade_id", nullable = false,
            foreignKey = @ForeignKey(name = "avaliacao_fk",
                    foreignKeyDefinition = "FOREIGN KEY (aluno_unidade_id) REFERENCES aluno_unidade (aluno_unidade_id) " +
                            "ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private AlunoUnidade alunoUnidade;
    @ManyToOne
    @JoinColumn(name = "status_id",
            foreignKey = @ForeignKey(name = "avaliacao_fk2",
                    foreignKeyDefinition = "FOREIGN KEY (status_id) REFERENCES status (status_id) ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private Status status;
    @Column(name = "nota")
    private Double nota;
    @Column(name = "data_realizacao")
    private LocalDateTime dataRealizacao;
    @OneToMany(mappedBy = "avaliacao",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AvaliacaoQuestaoAula> avaliacaoQuestaoAula;
    @Column(name = "aprovado", length = 1)
    private String aprovado;
}
