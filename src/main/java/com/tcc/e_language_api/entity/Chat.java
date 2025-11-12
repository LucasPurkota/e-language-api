package com.tcc.e_language_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(generator = "uuid_generate_v4()")
    @Column(name = "chat_id", nullable = false, unique = true)
    private UUID chatId;
    @ManyToOne
    @JoinColumn(name = "aluno_professor_id", nullable = false,
            foreignKey = @ForeignKey(name = "chat_fk",
                    foreignKeyDefinition = "FOREIGN KEY (aluno_professor_id) REFERENCES aluno_professor (aluno_professor_id) ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private AlunoProfessor alunoProfessor;
    @ManyToOne
    @JoinColumn(name = "remetente_id", nullable = false,
            foreignKey = @ForeignKey(name = "chat_fk2",
                    foreignKeyDefinition = "FOREIGN KEY (remetente_id) REFERENCES perfil (perfil_id) ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private Perfil remetente;
    @Column(name = "mensagem", nullable = false)
    private String mensagem;
    @Column(name = "data_envio", nullable = false)
    private LocalDateTime dataEnvio;
}
