package com.tcc.e_language_api.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "forma_pagamento")
public class FormaPagamento {
    @Id
    @Column(name = "forma_pagamento_id", nullable = false, unique = true)
    private int formaPagamentoId;
    @Column(name = "descricao", nullable = false)
    private String descricao;
}
