package com.projetolelabs.servico_de_intencao.controller

import com.projetolelabs.servico_de_intencao.service.ClienteService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

// Nome da variável fica esquerda e o tipo da variável na direita
@RestController
@RequestMapping("/api/clientes")
class ClienteController (val clienteService: ClienteService){

    @PostMapping
    fun criarClientes(@RequestBody clienteRequest:ClienteDTO): ClienteDTO {
        // clienteService é a minha variável referente ao serviço ClienteService
        // criarCliente(clienteRequest) é o métode que está dentro do ClienteService para criar o cliente
        // clienteRequest é a variável passada para criação do cliente
        return clienteService.criarCLiente(clienteRequest)
    }

    @GetMapping
    fun consultarClientes(@RequestParam("cpf") cpf: String): ClienteDTO? {

        return clienteService.consultarCliente(cpf)
    }

    @PutMapping
    fun atualizarClientes(@RequestBody clienteRequest:ClienteDTO): ClienteDTO? {

        return clienteService.atualizarCliente(clienteRequest)
    }

    @DeleteMapping
    fun excluirClientes(@RequestParam("cpf") cpf: String){

        clienteService.excluirCliente(cpf)
    }
}