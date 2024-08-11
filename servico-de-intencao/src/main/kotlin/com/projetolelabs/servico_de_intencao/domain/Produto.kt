package com.projetolelabs.servico_de_intencao.domain

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

// Em uma data class os valores são atribuidos durante a construção
// do objeto e não podem alterados depois, só podem ser consultados
// São chamados obejtos imutáveis
//@Embeddable
data class Produto @JsonCreator constructor(
    @JsonProperty("id") val id: Int?,
    @JsonProperty("title") val title: String?,
    @JsonProperty("price") val price: BigDecimal?,
    @JsonProperty("category") val category: String?,
    @JsonProperty("description") val description: String?,
    @JsonProperty("image") val image: String?,
    @JsonProperty("rating") val rating: Rating?
)