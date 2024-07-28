package com.projetolelabs.servico_de_intencao.repository

import com.projetolelabs.servico_de_intencao.domain.Cliente
import jakarta.transaction.Transactional
import java.util.Optional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ClienteRepository : JpaRepository<Cliente, Long> {

    fun findByCpf(cpf: String): Optional<Cliente>

    @Modifying
    @Transactional
    @Query("delete from clientes c where c.cpf = :cpf")
    fun deleteByCpf(@Param("cpf") cpf: String)
}