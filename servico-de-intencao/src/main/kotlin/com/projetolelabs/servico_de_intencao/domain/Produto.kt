package com.projetolelabs.servico_de_intencao.domain

import jakarta.persistence.Embeddable
import java.math.BigDecimal
// Em uma data class os valores são atribuidos durante a construção
// do objeto e não podem alterados depois, só podem ser consultados
// São chamados obejtos imutáveis
//@Embeddable
data class Produto(
    val id: Int?,
    val title: String?,
    val price: BigDecimal?,
    val category: String?,
    val description: String?,
    val image: String?,
    val rating: Rating?
)


