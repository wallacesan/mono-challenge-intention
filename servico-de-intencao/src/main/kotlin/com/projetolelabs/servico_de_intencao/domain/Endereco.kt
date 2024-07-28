package com.projetolelabs.servico_de_intencao.domain

import jakarta.persistence.Embeddable

@Embeddable
class Endereco(
    var rua: String?,
    var complemento: String?,
    var cidade: String?,
    var estado: String?,
    var cep: String?
)