package com.projetolelabs.servico_de_intencao.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.projetolelabs.servico_de_intencao.controller.IntencaoCompraDTO
import com.projetolelabs.servico_de_intencao.controller.IntencaoCompraResponse
import com.projetolelabs.servico_de_intencao.controller.IntencaoProdutoDTO
import com.projetolelabs.servico_de_intencao.domain.Intencao
import com.projetolelabs.servico_de_intencao.domain.Produto
import com.projetolelabs.servico_de_intencao.repository.ClienteRepository
import com.projetolelabs.servico_de_intencao.repository.IntencaoRepository

class IntencaoService(private val clienteRepository: ClienteRepository,
                      private val produtoExternoService: ProdutoExternoService,
                      private val intencaoRepository: IntencaoRepository,
                      private val objectMapper: ObjectMapper
) {

    fun criarIntencao(intencaoCompraDTO: IntencaoCompraDTO): IntencaoCompraResponse? {
        val cliente = clienteRepository.findByCpf(intencaoCompraDTO.cpf!!)

        if (cliente.isEmpty ){
            println("Cliente com cpf ${intencaoCompraDTO.cpf} não encontrado")
            return null
        }

        val produtos = intencaoCompraDTO.produtos?.map { produtoId ->
            produtoExternoService.getProdutoExterno(produtoId.toLong())
        }?.filterNotNull()?.toSet()

        if (produtos == null || produtos.isEmpty() ){
            println("Produtos ${intencaoCompraDTO.produtos} não encontrados!")
            return null
        }

        val produtosString = objectMapper.writeValueAsString(produtos)
        val intencao = Intencao(id = 0, nome = intencaoCompraDTO.nome, cliente = cliente.get(),
            produtos = produtosString)
        val intencaoEntity = intencaoRepository.save(intencao)
        return intencaoEntity.toIntencaoResponse(objectMapper)
    }

    // Criar uma função chamada "Buscar Intencão por Id" que receba uma id do tipo Long como argumento e retorne um objeto
    // do tipo "Intencão Produto"
    fun buscarIntencaoPorId(id: Long): IntencaoProdutoDTO? {
        val intencaoEntity = intencaoRepository.findById(id)


        if (intencaoEntity.isEmpty){
            return null
        }

        val idIntencao = intencaoEntity.get().id
        val produto = intencaoEntity.get().produtos
        val nome = intencaoEntity.get().nome
        val produtoObj: List<Produto?>? = objectMapper.readValue(produto, object : TypeReference<List<Produto?>?>() {})

        return IntencaoProdutoDTO(idIntencao, nome, produtoObj)
    }

    // Criar uma função que se chama excluir intencao que receba uma id do tipo long e não retorne nada

    fun excluirIntencao(id: Long) {
        intencaoRepository.deleteById(id)

    }

    // Criar uma função que irá consultar todas as intencoes do cliente por cpf, que irá retornar uma lista de intencoes
    fun buscarIntencoesPorCpf(cpf: String): List<IntencaoProdutoDTO> {
        val cliente = clienteRepository.findByCpf(cpf.toCpfFormatted()!!)

        if (cliente.isEmpty){
            return listOf()
        }

        val listaDTOS = mutableListOf<IntencaoProdutoDTO>()
        val lista = cliente.get().intencoes?.toList()
        for (i in lista!!.indices) {
            println("Index $i tem o valor ${lista[i]}")
            val intencao = lista[i]
            val produtoObj: List<Produto?>? = objectMapper.readValue(intencao.produtos, object : TypeReference<List<Produto?>?>() {})
            val dto = IntencaoProdutoDTO(intencao.id, nome = intencao.nome, produtoObj)
            listaDTOS.add(dto)
        }
        return listaDTOS

    }

    // Criar uma função que irá receber uma id do tipo long e uma lista de produtos para serem atualizados
    fun atualizarProdutos(id: Long, listaProdutos: List<String>): IntencaoCompraResponse? {
        val intencao = intencaoRepository.findById(id)

        if (intencao.isEmpty){
            return null
        }

        val produtos = listaProdutos.map { produtoId ->
            produtoExternoService.getProdutoExterno(produtoId.toLong())
        }?.filterNotNull()?.toSet()

        if (produtos == null || produtos.isEmpty() ){
            return null
        }
        intencao.get().apply {
            this.produtos = objectMapper.writeValueAsString(produtos)
        }
        return intencaoRepository.save(intencao.get()).toIntencaoResponse(objectMapper)
    }

}

private fun Intencao.toIntencaoResponse(objectMapper: ObjectMapper): IntencaoCompraResponse {
    val intencao = this
    val intencoesCliente = intencao.cliente.intencoes?.map {
        val produtoObj: List<Produto?>? = objectMapper.readValue(it.produtos, object : TypeReference<List<Produto?>?>() {})
        IntencaoProdutoDTO(idIntencao = it.id, nome = it.nome, produtos = produtoObj)
    }?.toList()

    return IntencaoCompraResponse(cliente = intencao.cliente.toClienteDTO(), intencoes = intencoesCliente )
}
