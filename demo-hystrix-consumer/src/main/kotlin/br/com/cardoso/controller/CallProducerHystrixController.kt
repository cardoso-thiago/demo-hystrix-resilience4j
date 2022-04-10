package br.com.cardoso.controller

import br.com.cardoso.service.CallProducerHystrixService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CallProducerHystrixController(private val callProducerHystrixService: CallProducerHystrixService) {

    @GetMapping("hystrix/hello/{name}")
    fun getHystrixHello(@PathVariable("name") name: String) = callProducerHystrixService.getHello(name)
}