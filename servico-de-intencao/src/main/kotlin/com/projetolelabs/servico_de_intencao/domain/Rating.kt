package com.projetolelabs.servico_de_intencao.domain

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Rating @JsonCreator constructor(
    @JsonProperty("rate") val rate: Double?,
    @JsonProperty("count") val count: Int?,
)