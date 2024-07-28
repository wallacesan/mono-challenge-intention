package com.projetolelabs.servico_de_intencao.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne


@Entity(name = "intencao_compras")
class Intencao(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
    var nome: String?,
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    val cliente: Cliente,
    @Column(columnDefinition = "json")
    var produtos: String
)