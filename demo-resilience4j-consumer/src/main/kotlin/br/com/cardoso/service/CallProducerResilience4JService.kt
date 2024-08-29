package br.com.cardoso.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


@Service
class CallProducerResilience4JService(private val circuitBreakerFactory: Resilience4JCircuitBreakerFactory) {

    val logger: Logger = LoggerFactory.getLogger(CallProducerResilience4JService::class.java)

    fun getHello(name: String): String {
        val circuitBreaker = circuitBreakerFactory.create("hello")
        return circuitBreaker.run({
            RestTemplate().getForObject(
                "http://localhost:8080/hello/{name}", String::class.java, name
            )
        }, { throwable: Throwable -> defaultName(throwable) })
    }

    fun defaultName(throwable: Throwable): String {
        logger.error("Erro ao obter o nome, será devolvido um nome padrão. Mensagem de retorno => ${throwable.message}")
        return "Hello John Doe"
    }
}