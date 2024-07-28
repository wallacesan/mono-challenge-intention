package com.projetolelabs.servico_de_intencao.controller

import com.projetolelabs.servico_de_intencao.service.IntencaoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/intencoes")
class IntencaoController (val intencaoService: IntencaoService){


    @PostMapping
    fun criarIntencao(@RequestBody intencao:IntencaoCompraDTO): ResponseEntity<IntencaoCompraResponse?> {
        val novaIntencao = intencaoService.criarIntencao(intencao) ?: return ResponseEntity.badRequest().build()

        return ResponseEntity.status(HttpStatus.CREATED).body(novaIntencao)
    }

    @PatchMapping("/intencao/{id}")
    fun atualizarIntencao(@PathVariable("id") id:Long, @RequestBody produtos:List<String>): ResponseEntity<IntencaoCompraResponse> {
        val intencao = intencaoService.atualizarProdutos(id, produtos)

        if (intencao == null){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(intencao)
    }

    @GetMapping
    fun obterIntencoesPorCpf(@RequestParam("cpf") cpf:String): ResponseEntity<List<IntencaoProdutoDTO>> {
        val lista = intencaoService.buscarIntencoesPorCpf(cpf)

        if (lista.isEmpty()){
            return ResponseEntity.notFound().build()
        }

        return ResponseEntity.ok(lista)
    }

    @DeleteMapping("/intencao/{id}")
    fun excluirIntencao(@PathVariable("id") id:Long) {
        intencaoService.excluirIntencao(id)
    }

    @GetMapping("/intencao/{id}")
    fun obterIntencaoPorIdIntencao(@PathVariable("id") id:Long): ResponseEntity<IntencaoProdutoDTO> {
        val intencao = intencaoService.buscarIntencaoPorId(id)

        if (intencao == null){
            return ResponseEntity.notFound().build()
        }

        return ResponseEntity.ok(intencao)
    }
}