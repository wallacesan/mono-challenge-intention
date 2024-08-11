package com.projetolelabs.servico_de_intencao.config

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.projetolelabs.servico_de_intencao.domain.Produto
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class ProdutoConverter : AttributeConverter<List<Produto>, String> {
    private val objectMapper = ObjectMapper()

    override fun convertToDatabaseColumn(produto: List<Produto>?): String {
        println("Convertendo produto $produto para String")
        return objectMapper.writeValueAsString(produto)
    }

    override fun convertToEntityAttribute(produtoJSON: String?): List<Produto> {
        println("Convertendo uma String JSON $produtoJSON para Produto ")
        return objectMapper.readValue(produtoJSON, object : TypeReference<List<Produto>>() {})
    }
}