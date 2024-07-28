package com.projetolelabs.servico_de_intencao.controller

import com.projetolelabs.servico_de_intencao.domain.Produto
import com.projetolelabs.servico_de_intencao.service.ProdutoExternoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/produtos")
class ProdutoController (val produtoService: ProdutoExternoService){

    @GetMapping
    fun consultarProduto(@RequestParam(value = "productId") produtoId:Long): Produto? {
        return produtoService.getProdutoExterno(produtoId)
    }
}