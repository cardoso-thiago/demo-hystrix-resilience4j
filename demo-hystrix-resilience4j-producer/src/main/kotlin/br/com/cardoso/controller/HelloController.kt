package br.com.cardoso.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    val logger: Logger = LoggerFactory.getLogger(HelloController::class.java)
    var counter: Int = 0

    @GetMapping("/hello/{name}")
    fun hello(@PathVariable("name") name: String): ResponseEntity<String> {
        counter++
        logger.info("Chamada número $counter")

        if (name == "error") {
            return ResponseEntity.badRequest().body("Erro na chamada")
        }
        return ResponseEntity.ok("Hello $name")
    }
}