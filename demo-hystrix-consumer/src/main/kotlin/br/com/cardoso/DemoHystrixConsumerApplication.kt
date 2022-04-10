package br.com.cardoso

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker

@EnableCircuitBreaker
@SpringBootApplication
class DemoHystrixConsumerApplication

fun main(args: Array<String>) {
    runApplication<DemoHystrixConsumerApplication>(*args)
}
