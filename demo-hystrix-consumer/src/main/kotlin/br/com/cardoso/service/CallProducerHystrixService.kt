package br.com.cardoso.service

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class CallProducerHystrixService {

    @HystrixCommand(fallbackMethod = "defaultName")
    fun getHello(name: String) = RestTemplate().getForObject(
        "http://localhost:8080/hello/{name}", String::class.java, name
    )

    fun defaultName(name: String) = "Hello John Doe"
}
