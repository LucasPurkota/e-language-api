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
@Table(name = "avaliacao_satisfacao_professor")
public class AvaliacaoSatisfacaoProfessor {
    @Id
    @GeneratedValue(generator = "uuid_generate_v4()")
    @Column(name = "avaliacao_satisfacao_professor_id", nullable = false, unique = true)
    private UUID avaliacaoSatisfacaoProfessorId;
    @ManyToOne
    @JoinColumn(name = "aluno_professor_id", nullable = false,
            foreignKey = @ForeignKey(name = "avaliacao_satisfacao_professor_fk",
                    foreignKeyDefinition = "FOREIGN KEY (aluno_professor_id) REFERENCES aluno_professor (aluno_professor_id) ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private AlunoProfessor alunoProfessor;
    @Column(name = "avaliacao", nullable = false)
    private String avaliacao;
    @Column(name = "pontos", nullable = false)
    private int pontos;
}
