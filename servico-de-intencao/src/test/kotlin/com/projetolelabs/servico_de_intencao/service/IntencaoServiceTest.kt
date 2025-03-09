package com.projetolelabs.servico_de_intencao.service

import com.projetolelabs.servico_de_intencao.controller.IntencaoCompraDTO
import com.projetolelabs.servico_de_intencao.domain.Cliente
import com.projetolelabs.servico_de_intencao.domain.Endereco
import com.projetolelabs.servico_de_intencao.domain.Intencao
import com.projetolelabs.servico_de_intencao.domain.Produto
import com.projetolelabs.servico_de_intencao.domain.Rating
import com.projetolelabs.servico_de_intencao.repository.ClienteRepository
import com.projetolelabs.servico_de_intencao.repository.IntencaoRepository
import java.math.BigDecimal
import java.util.Optional
import kotlin.test.Test
import kotlin.test.assertEquals
import org.mockito.Mockito.any
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.verify

class IntencaoServiceTest{
    private val clienteRepository = mock<ClienteRepository>()
    private val produtoExternoService = mock<ProdutoExternoService>()
    private val intencaoRepository = mock<IntencaoRepository>()
    private val serviceTest = IntencaoService(clienteRepository, produtoExternoService, intencaoRepository)

    @Test
    fun `nao deve criar uma intencao de compra quando cliente nao e encontrado`() {
        `when`(clienteRepository.findByCpf("156.653.557-39")).thenReturn(Optional.empty())

        val intencao = IntencaoCompraDTO("156.653.557-39", "Lista de Natal", listOf("1", "2"))
        val resultado = serviceTest.criarIntencao(intencao)
        assertEquals(null, resultado, "Cliente não encontrado")
    }

    @Test
    fun `nao deve criar uma intencao de compra quando o produto nao e encontrado`() {
        val cliente = Cliente(id = 1, nome = "Yasmin dos Santos", cpf = "156.653.557-39",
            email = "wallacesantos.m@gmail.com", endereco = Endereco(rua = "rua test", complemento = "apto 10",
                cidade = "Rio", estado = "RJ", cep = "20230011"), telefone = "2120202020")

        `when`(clienteRepository.findByCpf("156.653.557-39")).thenReturn(Optional.of(cliente))

        val intencao = IntencaoCompraDTO("156.653.557-39", "Lista de Natal", listOf("1", "2"))
        val resultado = serviceTest.criarIntencao(intencao)
        assertEquals(null, resultado, "Cliente não encontrado")
    }

    @Test
    fun `deve criar uma intencao de compra quando o produto e encontrado`() {
        val produto1 = Produto(id = 1, title = "Produto Test1", price = BigDecimal(89.00),
            category = "Ferramentas", description = "Parafusadeira", rating = Rating(rate = 5.0, count = 10),
            image = null
        )

        `when`(produtoExternoService.getProdutoExterno(1)).thenReturn(produto1)

        val produto2 = Produto(id = 2, title = "Produto Test2", price = BigDecimal(99.00),
            category = "Ferramentas", description = "Esmerilhadeira", rating = Rating(rate = 7.0, count = 20),
            image = null
        )

        `when`(produtoExternoService.getProdutoExterno(2)).thenReturn(produto2)

        val cliente = Cliente(id = 1, nome = "Yasmin dos Santos", cpf = "156.653.557-39",
            email = "wallacesantos.m@gmail.com", endereco = Endereco(rua = "rua test", complemento = "apto 10",
                cidade = "Rio", estado = "RJ", cep = "20230011"), telefone = "2120202020")
        `when`(clienteRepository.findByCpf("156.653.557-39")).thenReturn(Optional.of(cliente))

        val intencaoArg = argumentCaptor<Intencao>()
        val intencao1 = mock<Intencao>()

        `when`(intencao1.produtos).thenReturn(listOf(produto1, produto2))
        `when`(intencao1.cliente).thenReturn(cliente)
        `when`(intencao1.nome).thenReturn("lista de Natal")
        `when`(intencaoRepository.save(any())).thenReturn(intencao1)

        val intencao = IntencaoCompraDTO("156.653.557-39", "Lista de Natal", listOf("1", "2"))
        serviceTest.criarIntencao(intencao)
        verify(intencaoRepository).save(intencaoArg.capture())
        assertEquals("Lista de Natal", intencaoArg.firstValue.nome)
        assertEquals("156.653.557-39", intencaoArg.firstValue.cliente.cpf)
        assertEquals("Yasmin dos Santos", intencaoArg.firstValue.cliente.nome)
        assertEquals("wallacesantos.m@gmail.com", intencaoArg.firstValue.cliente.email)
        assertEquals("2120202020", intencaoArg.firstValue.cliente.telefone)
        assertEquals("rua test", intencaoArg.firstValue.cliente.endereco!!.rua)
        assertEquals("apto 10", intencaoArg.firstValue.cliente.endereco!!.complemento)
        assertEquals("Rio", intencaoArg.firstValue.cliente.endereco!!.cidade)
        assertEquals("RJ", intencaoArg.firstValue.cliente.endereco!!.estado)
        assertEquals("20230011", intencaoArg.firstValue.cliente.endereco!!.cep)
        assertEquals(1, intencaoArg.firstValue.produtos[0].id)
        assertEquals("Produto Test1", intencaoArg.firstValue.produtos[0].title)
        assertEquals(BigDecimal(89.00), intencaoArg.firstValue.produtos[0].price)
        assertEquals("Ferramentas", intencaoArg.firstValue.produtos[0].category)
        assertEquals("Parafusadeira", intencaoArg.firstValue.produtos[0].description)
        assertEquals(5.0, intencaoArg.firstValue.produtos[0].rating!!.rate)
        assertEquals(10, intencaoArg.firstValue.produtos[0].rating!!.count)
        assertEquals(null, intencaoArg.firstValue.produtos[0].image)

        assertEquals(2, intencaoArg.firstValue.produtos[1].id)
        assertEquals("Produto Test2", intencaoArg.firstValue.produtos[1].title)
        assertEquals(BigDecimal(99.00), intencaoArg.firstValue.produtos[1].price)
        assertEquals("Ferramentas", intencaoArg.firstValue.produtos[1].category)
        assertEquals("Esmerilhadeira", intencaoArg.firstValue.produtos[1].description)
        assertEquals(7.0, intencaoArg.firstValue.produtos[1].rating!!.rate)
        assertEquals(20, intencaoArg.firstValue.produtos[1].rating!!.count)
        assertEquals(null, intencaoArg.firstValue.produtos[1].image)
    }

    @Test
    fun `deve buscar uma intencao de compra por id`() {
        val produto1 = Produto(id = 1, title = "Produto Test1", price = BigDecimal(89.00),
            category = "Ferramentas", description = "Parafusadeira", rating = Rating(rate = 5.0, count = 10),
            image = null
        )

        val cliente = Cliente(id = 1, nome = "Yasmin dos Santos", cpf = "156.653.557-39",
            email = "wallacesantos.m@gmail.com", endereco = Endereco(rua = "rua test", complemento = "apto 10",
                cidade = "Rio", estado = "RJ", cep = "20230011"), telefone = "2120202020")

        val intencao = Intencao(id = 1, nome = "Lista 01", cliente = cliente, produtos = listOf(produto1))
        `when`(intencaoRepository.findById(1)).thenReturn(Optional.of(intencao))

        val resultado = serviceTest.buscarIntencaoPorId(1)

        assertEquals(1, resultado!!.produtos?.get(0)?.id)
        assertEquals(1, resultado!!.idIntencao)
        assertEquals("Lista 01", resultado!!.nome)
    }

}