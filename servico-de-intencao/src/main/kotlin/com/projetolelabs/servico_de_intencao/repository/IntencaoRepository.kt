package com.projetolelabs.servico_de_intencao.repository

import com.projetolelabs.servico_de_intencao.domain.Cliente
import com.projetolelabs.servico_de_intencao.domain.Intencao
import jakarta.transaction.Transactional
import java.util.Optional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface IntencaoRepository : JpaRepository<Intencao, Long> {

}