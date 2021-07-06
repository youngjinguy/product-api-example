package com.youngjinguy.product.controller

import com.youngjinguy.product.model.ProductCreateRequest
import com.youngjinguy.product.model.ProductUpdateRequest
import com.youngjinguy.product.service.ProductService
import org.springframework.web.bind.annotation.*
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

    @PutMapping("/{no}")
    fun create(@RequestBody request: ProductUpdateRequest, @PathVariable("no") productNo: Long): Mono<Long> {
        productService.update(productNo = productNo, request = request)
        return Mono.just(productNo)
    }
}
