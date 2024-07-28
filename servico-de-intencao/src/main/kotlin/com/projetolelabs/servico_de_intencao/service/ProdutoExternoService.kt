package com.projetolelabs.servico_de_intencao.service

import com.projetolelabs.servico_de_intencao.domain.Produto
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class ProdutoExternoService(val restTemplate: RestTemplate){
    fun getProdutoExterno(productId:Long): Produto? {
        // Aqui fazemos uma consulta ao servico de intencao para buscar as informações do produto //
        // A consulta é feita via comunicação http com restApi //
        val url = "http://localhost:3000/api/products/id/$productId"
        return restTemplate.getForObject(url, Produto::class.java)
    }
}