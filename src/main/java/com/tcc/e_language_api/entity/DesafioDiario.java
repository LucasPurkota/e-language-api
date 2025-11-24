package com.tcc.e_language_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "desafio_diario")
public class DesafioDiario {
    @Id
    @GeneratedValue(generator = "uuid_generate_v4()")
    @Column(name = "desafio_diario_id", nullable = false, unique = true)
    private UUID desafioDiarioId;
    @ManyToOne
    @JoinColumn(name = "perfil_idioma_id", nullable = false,
            foreignKey = @ForeignKey(name = "desafio_diario_fk",
                    foreignKeyDefinition = "FOREIGN KEY (perfil_idioma_id) REFERENCES perfil_idioma (perfil_idioma_id) ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private PerfilIdioma perfilIdioma;
    @ManyToOne
    @JoinColumn(name = "questao_aula_id", nullable = false,
            foreignKey = @ForeignKey(name = "desafio_diario_fk2",
                    foreignKeyDefinition = "FOREIGN KEY (questao_aula_id) REFERENCES questao_aula (questao_aula_id) ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private QuestaoAula questaoAula;
    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false,
            foreignKey = @ForeignKey(name = "desafio_diario_fk3",
                    foreignKeyDefinition = "FOREIGN KEY (status_id) REFERENCES status (status_id) ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private Status status;
    @Column(name = "data_desafio", nullable = false)
    private LocalDate dataDesafio;
    @Column(name = "resposta")
    private String resposta;
    @Column(name = "correto", length = 1)
    private String correto;
}
