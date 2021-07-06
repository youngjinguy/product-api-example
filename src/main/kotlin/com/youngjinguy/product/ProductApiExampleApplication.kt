package com.youngjinguy.product

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.reactive.config.EnableWebFlux

@SpringBootApplication
@EnableWebFlux
class ProductApiExampleApplication

fun main(args: Array<String>) {
    runApplication<ProductApiExampleApplication>(*args)
}
