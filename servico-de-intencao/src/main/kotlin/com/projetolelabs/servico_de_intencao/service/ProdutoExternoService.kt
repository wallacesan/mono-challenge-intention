package com.projetolelabs.servico_de_intencao.service

import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class ProdutoExternoService(val restTemplate: RestTemplate){
    fun getProdutoExterno(productId:Long): String {
        val url = "http://localhost:8080/api/products/id/$productId"
        return restTemplate.getForObject(url, String::class.java) ?: "Dados não encontrados"
    }
}