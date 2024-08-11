package com.projetolelabs.servico_de_intencao.domain

import com.projetolelabs.servico_de_intencao.config.ProdutoConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
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
    @JoinColumn(name = "client_id")
    val cliente: Cliente,
    @Column(columnDefinition = "json")
    @Convert(converter = ProdutoConverter::class)
    var produtos: List<Produto>
)