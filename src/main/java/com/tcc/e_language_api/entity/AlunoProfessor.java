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
@Table(name = "aluno_professor")
public class AlunoProfessor {
    @Id
    @GeneratedValue(generator = "uuid_generate_v4()")
    @Column(name = "aluno_professor_id", nullable = false, unique = true)
    private UUID alunoProfessorId;
    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false,
            foreignKey = @ForeignKey(name = "aluno_professor_fk",
                    foreignKeyDefinition = "FOREIGN KEY (aluno_id) REFERENCES perfil (perfil_id) ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private Perfil aluno;
    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false,
            foreignKey = @ForeignKey(name = "aluno_professor_fk2",
                    foreignKeyDefinition = "FOREIGN KEY (professor_id) REFERENCES perfil (perfil_id) ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private Perfil professor;
}
