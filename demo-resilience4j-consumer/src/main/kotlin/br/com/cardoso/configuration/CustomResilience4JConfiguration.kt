package br.com.cardoso.configuration

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder
import org.springframework.cloud.client.circuitbreaker.Customizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
class CustomResilience4JConfiguration {

    @Bean
    fun helloCustomizer(): Customizer<Resilience4JCircuitBreakerFactory> {
        return Customizer { factory: Resilience4JCircuitBreakerFactory ->
            factory.configure(
                { builder: Resilience4JConfigBuilder ->
                    builder.circuitBreakerConfig(
                        CircuitBreakerConfig.custom()
                            .failureRateThreshold(50.0f)
                            .minimumNumberOfCalls(5)
                            .automaticTransitionFromOpenToHalfOpenEnabled(true)
                            .waitDurationInOpenState(Duration.ofSeconds(5))
                            .permittedNumberOfCallsInHalfOpenState(3)
                            .slidingWindowSize(10)
                            .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                            .build()
                    )
                }, "hello"
            )
        }
    }
}