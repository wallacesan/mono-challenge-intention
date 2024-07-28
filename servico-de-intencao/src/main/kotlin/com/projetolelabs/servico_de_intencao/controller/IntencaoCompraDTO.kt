package com.projetolelabs.servico_de_intencao.controller

data class IntencaoCompraDTO(
    val cpf: String?,
    val nome: String,
    val produtos: List<String>?
)