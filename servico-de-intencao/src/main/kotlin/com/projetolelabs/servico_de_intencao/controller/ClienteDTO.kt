package com.projetolelabs.servico_de_intencao.controller

data class ClienteDTO(
    val nome: String?,
    val cpf: String?,
    val telefone: String?,
    val email: String?,
    val rua: String?,
    val complemento: String?,
    val cidade: String?,
    val estado: String?,
    val cep: String?
)