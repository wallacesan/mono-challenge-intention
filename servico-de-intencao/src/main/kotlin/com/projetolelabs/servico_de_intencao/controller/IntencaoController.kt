package com.projetolelabs.servico_de_intencao.controller

import com.projetolelabs.servico_de_intencao.service.ProdutoExternoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/intencoes-compra")
class IntencaoController (val produtoService: ProdutoExternoService){

    @GetMapping("/produto")
    fun consultarProduto(@RequestParam(value = "productId") produtoId:Long): String {
        return produtoService.getProdutoExterno(produtoId)
    }
}