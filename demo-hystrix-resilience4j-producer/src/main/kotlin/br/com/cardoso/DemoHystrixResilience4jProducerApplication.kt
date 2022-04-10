package br.com.cardoso

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoHystrixResilience4jProducerApplication

fun main(args: Array<String>) {
	runApplication<DemoHystrixResilience4jProducerApplication>(*args)
}
