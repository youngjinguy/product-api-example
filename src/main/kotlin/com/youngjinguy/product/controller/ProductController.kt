package com.youngjinguy.product.controller

import com.youngjinguy.product.model.ProductCreateRequest
import com.youngjinguy.product.service.ProductService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

/**
 * @author Youngjin Kim
 * @since 2021-07-06
 */
@RestController
@RequestMapping("/products")
class ProductController(
    private val productService: ProductService
) {

    @PostMapping("")
    fun create(@RequestBody request: ProductCreateRequest): Mono<Long> {
        return Mono.just(productService.create(request))
    }
}
