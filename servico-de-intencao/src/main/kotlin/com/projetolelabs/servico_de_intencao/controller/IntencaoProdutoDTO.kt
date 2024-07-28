package com.projetolelabs.servico_de_intencao.controller

import com.projetolelabs.servico_de_intencao.domain.Produto

data class IntencaoProdutoDTO(
    val idIntencao: Long?,
    var nome: String?,
    val produtos: List<Produto?>?

)