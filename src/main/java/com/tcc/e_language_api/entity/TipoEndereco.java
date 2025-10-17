package com.tcc.e_language_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tipo_endereco")
public class TipoEndereco {
    @Id
    @Column(name = "tipo_endereco_id", nullable = false, unique = true)
    private int tipoEnderecoId;
    @Column(name = "descricao", nullable = false)
    private String descricao;
}
