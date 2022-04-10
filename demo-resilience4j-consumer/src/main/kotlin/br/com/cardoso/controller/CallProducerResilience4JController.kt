package br.com.cardoso.controller

import br.com.cardoso.service.CallProducerResilience4JService
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CallProducerResilience4JController(
    private val callProducerResilience4JService: CallProducerResilience4JService,
    private val circuitBreakerFactory: Resilience4JCircuitBreakerFactory
) {

    @GetMapping("resilience4J/hello/{name}")
    fun getResilience4JHello(@PathVariable("name") name: String) = callProducerResilience4JService.getHello(name)

    @GetMapping("resilience4J/disable")
    fun disableCircuitBreaker() {
        val allCircuitBreakers = circuitBreakerFactory.circuitBreakerRegistry.allCircuitBreakers
        for (circuitBreaker in allCircuitBreakers) {
            println(circuitBreaker.state)
            circuitBreaker.transitionToDisabledState()
            println(circuitBreaker.state)
        }
    }

    @GetMapping("resilience4J/enable")
    fun enableCircuitBreaker() {
        val allCircuitBreakers = circuitBreakerFactory.circuitBreakerRegistry.allCircuitBreakers
        for (circuitBreaker in allCircuitBreakers) {
            println(circuitBreaker.state)
            circuitBreaker.transitionToClosedState()
            println(circuitBreaker.state)
        }
    }
}