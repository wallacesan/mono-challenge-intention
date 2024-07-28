package com.projetolelabs.servico_de_intencao.service

import com.projetolelabs.servico_de_intencao.controller.ClienteDTO
import com.projetolelabs.servico_de_intencao.domain.Cliente
import com.projetolelabs.servico_de_intencao.domain.Endereco
import com.projetolelabs.servico_de_intencao.repository.ClienteRepository
import org.springframework.stereotype.Service

@Service
class ClienteService(private val repository: ClienteRepository){


    fun criarCLiente(clienteRequest: ClienteDTO): ClienteDTO{

        val cliente = clienteRequest.toCliente()
        val clienteEntity = repository.save(cliente)

        return clienteEntity.toClienteDTO()
    }

    fun consultarCliente(cpf: String): ClienteDTO?{
        //Quando usa !! indica que o valor nâo será nulo
        // Se for nulo dará exception
        val clienteEntity = repository.findByCpf(cpf.toCpfFormatted()!!)

        if (clienteEntity.isEmpty){
            println("Cliente com CPF $cpf não foi encontrado")
            return null
        }

        return clienteEntity.get().toClienteDTO()

    }

    fun excluirCliente(cpf: String){
        repository.deleteByCpf(cpf.toCpfFormatted()!!)
    }

    fun atualizarCliente(clienteRequest: ClienteDTO): ClienteDTO?{

        val clienteByCpf = repository.findByCpf(clienteRequest.cpf.toCpfFormatted()!!)

        if (clienteByCpf.isEmpty){
            println("Cliente com CPF ${clienteRequest.cpf.toCpfFormatted()} não foi encontrado")
            return null
        }

        val enderecoSave = clienteByCpf.get().endereco

        enderecoSave?.apply {
            rua = clienteRequest.rua
            complemento = clienteRequest.complemento
            cidade = clienteRequest.cidade
            estado = clienteRequest.estado
            cep = clienteRequest.cep
        }

        clienteByCpf.get().apply {
            nome = clienteRequest.nome
            telefone = clienteRequest.telefone
            email = clienteRequest.email
            endereco = enderecoSave
        }

        val clienteEntity = repository.save(clienteByCpf.get())
        return clienteEntity.toClienteDTO()
    }

}

private fun ClienteDTO.toCliente(): Cliente {
    val clienteDTO = this

    val endereco = Endereco(rua = clienteDTO.rua, complemento = clienteDTO.complemento, cidade = clienteDTO.cidade,
        estado = clienteDTO.cidade, cep = clienteDTO.cep)

    return Cliente(cpf = clienteDTO.cpf.toCpfFormatted(), nome = clienteDTO.nome, endereco = endereco,
        email = clienteDTO.email, telefone = clienteDTO.telefone)
}

fun Cliente.toClienteDTO(): ClienteDTO {
    val clienteEntity = this
    val enderecoEntity = this.endereco
    return ClienteDTO(nome = clienteEntity.nome, cpf = clienteEntity.cpf.toCpfFormatted(),
        email = clienteEntity.email,
        telefone = clienteEntity.telefone, rua = enderecoEntity?.rua,
        complemento = enderecoEntity?.complemento,
        cidade = enderecoEntity?.cidade, estado = enderecoEntity?.estado,
        cep = enderecoEntity?.cep)
}

fun String?.toCpfFormatted(): String? {
    val cpf = this
    // Remove todos os caracteres não numéricos
    val cleanedCpf = cpf?.replace(Regex("[^\\d]"), "")

    // Verifica se o CPF tem exatamente 11 dígitos
    if (cleanedCpf?.length != 11) {
        throw IllegalArgumentException("CPF deve conter exatamente 11 dígitos")
    }

    // Formata o CPF usando a expressão regular
    return cleanedCpf.replace(Regex("(\\d{3})(\\d{3})(\\d{3})(\\d{2})"), "$1.$2.$3-$4")
}