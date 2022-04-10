package br.com.cardoso.service

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class CallProducerResilience4JService(private val circuitBreakerFactory: Resilience4JCircuitBreakerFactory) {

    fun getHello(name: String): String {
        val circuitBreaker = circuitBreakerFactory.create("hello")
        println(circuitBreakerFactory.circuitBreakerRegistry.circuitBreaker("hello").state)
        return circuitBreaker.run({
            RestTemplate().getForObject(
                "http://localhost:8080/hello/{name}", String::class.java, name
            )
        }, { defaultName() })
    }

    fun defaultName() = "Hello John Doe"
}