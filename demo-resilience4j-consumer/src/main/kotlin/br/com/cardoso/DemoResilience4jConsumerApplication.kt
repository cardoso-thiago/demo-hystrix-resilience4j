package br.com.cardoso

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoResilience4jConsumerApplication

fun main(args: Array<String>) {
	runApplication<DemoResilience4jConsumerApplication>(*args)
}
