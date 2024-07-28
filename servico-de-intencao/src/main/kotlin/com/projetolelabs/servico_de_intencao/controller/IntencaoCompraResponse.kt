package com.projetolelabs.servico_de_intencao.controller

data class IntencaoCompraResponse(
    val cliente: ClienteDTO,
    val intencoes: List<IntencaoProdutoDTO>?

)