package com.projetolelabs.servico_de_intencao.domain

import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany


@Entity(name = "clientes")
class Cliente(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
    var nome: String?,
    val cpf: String?,
    var telefone: String?,
    var email: String?,
    @Embedded var endereco: Endereco?,
    @OneToMany(mappedBy = "cliente")
    var intencoes: Set<Intencao>? = setOf()

)