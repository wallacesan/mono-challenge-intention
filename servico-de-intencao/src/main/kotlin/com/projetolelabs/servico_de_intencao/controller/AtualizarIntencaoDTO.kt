package com.projetolelabs.servico_de_intencao.controller

data class AtualizarIntencaoDTO(
    val acao: IntencaoTipoUpdate = IntencaoTipoUpdate.SUBSTITUIR,
    val produtos: List<String>?

)
