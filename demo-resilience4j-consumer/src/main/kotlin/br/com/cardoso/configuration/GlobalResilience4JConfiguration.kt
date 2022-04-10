package br.com.cardoso.configuration

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.timelimiter.TimeLimiterConfig
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder
import org.springframework.cloud.client.circuitbreaker.Customizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import java.time.Duration
import java.util.*

@Configuration
class GlobalResilience4JConfiguration {

    @Bean
    fun defaultCustomizer(environment: Environment): Customizer<Resilience4JCircuitBreakerFactory> {
        val timeout = Optional.ofNullable(environment["config.timeout"]).orElse("1").toLong()
        return Customizer<Resilience4JCircuitBreakerFactory> { factory ->
            factory.configureDefault { id ->
                Resilience4JConfigBuilder(id).timeLimiterConfig(
                    TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(timeout)).build()
                ).circuitBreakerConfig(CircuitBreakerConfig.ofDefaults()).build()
            }
        }
    }

    @Bean
    fun helloCustomizer(environment: Environment): Customizer<Resilience4JCircuitBreakerFactory> {
        val timeout = Optional.ofNullable(environment["config.timeout"]).orElse("1").toLong()
        return Customizer { factory: Resilience4JCircuitBreakerFactory ->
            factory.configure(
                { builder: Resilience4JConfigBuilder ->
                    builder.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults()).timeLimiterConfig(
                        TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(timeout)).build()
                    )
                }, "hello"
            )
        }
    }
}