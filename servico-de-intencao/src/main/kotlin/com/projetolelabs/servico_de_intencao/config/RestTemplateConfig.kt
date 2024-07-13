package com.projetolelabs.servico_de_intencao.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class RestTemplateConfig {

    @Bean
    fun restTemplate():RestTemplate{
        return RestTemplate()
    }
}