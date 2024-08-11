package com.projetolelabs.servico_de_intencao.service

import com.projetolelabs.servico_de_intencao.controller.AtualizarIntencaoDTO
import com.projetolelabs.servico_de_intencao.controller.IntencaoCompraDTO
import com.projetolelabs.servico_de_intencao.controller.IntencaoCompraResponse
import com.projetolelabs.servico_de_intencao.controller.IntencaoProdutoDTO
import com.projetolelabs.servico_de_intencao.controller.IntencaoTipoUpdate
import com.projetolelabs.servico_de_intencao.domain.Intencao
import com.projetolelabs.servico_de_intencao.repository.ClienteRepository
import com.projetolelabs.servico_de_intencao.repository.IntencaoRepository
import org.springframework.stereotype.Service

@Service
class IntencaoService(private val clienteRepository: ClienteRepository,
                      private val produtoExternoService: ProdutoExternoService,
                      private val intencaoRepository: IntencaoRepository
) {

    fun criarIntencao(intencaoCompraDTO: IntencaoCompraDTO): IntencaoCompraResponse? {
        val cliente = clienteRepository.findByCpf(intencaoCompraDTO.cpf!!)

        if (cliente.isEmpty ){
            println("Cliente com cpf ${intencaoCompraDTO.cpf} não encontrado")
            return null
        }

        val produtos = intencaoCompraDTO.produtos?.mapNotNull { produtoId ->
            produtoExternoService.getProdutoExterno(produtoId.toLong())
        }?.toSet()

        if (produtos.isNullOrEmpty()){
            println("Produtos ${intencaoCompraDTO.produtos} não encontrados!")
            return null
        }


        val intencao = Intencao(id = 0, nome = intencaoCompraDTO.nome, cliente = cliente.get(),
            produtos = produtos.toList())
        val intencaoEntity = intencaoRepository.save(intencao)
        return intencaoEntity.toIntencaoResponse()
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

        return IntencaoProdutoDTO(idIntencao, nome, produto)
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
            val dto = IntencaoProdutoDTO(intencao.id, nome = intencao.nome, intencao.produtos)
            listaDTOS.add(dto)
        }
        return listaDTOS

    }

    // Criar uma função que irá receber uma id do tipo long e uma lista de produtos para serem atualizados
    fun atualizarProdutos(id: Long, atualizacaoDTO: AtualizarIntencaoDTO): IntencaoCompraResponse? {
        val intencao = intencaoRepository.findById(id)

        if (intencao.isEmpty){
            return null
        }

        val listaProdutos = atualizacaoDTO.produtos

        var produtos = listaProdutos?.mapNotNull { produtoId ->
            produtoExternoService.getProdutoExterno(produtoId.toLong())
        }?.toSet()

        val produtosOriginal = intencao.get().produtos.toMutableSet()

        if (produtos == null || produtos.isEmpty()){
            return null

        } else{
            if (atualizacaoDTO.acao == IntencaoTipoUpdate.ADICIONAR){
                produtosOriginal.addAll(produtos)
                produtos = produtosOriginal
            }
            if (atualizacaoDTO.acao == IntencaoTipoUpdate.REMOVER){
                produtosOriginal.removeAll(produtos)
                produtos = produtosOriginal
            }
        }

        intencao.get().apply {
            this.produtos = produtos.toList()
        }
        return intencaoRepository.save(intencao.get()).toIntencaoResponse()
    }

}

private fun Intencao.toIntencaoResponse(): IntencaoCompraResponse {
    val intencao = this
    val intencoesCliente = intencao.cliente.intencoes?.map {
        IntencaoProdutoDTO(idIntencao = it.id, nome = it.nome, produtos = it.produtos)
    }?.toList()

    return IntencaoCompraResponse(cliente = intencao.cliente.toClienteDTO(), intencoes = intencoesCliente )
}
