package com.youngjinguy.product.controller

import com.youngjinguy.product.model.ProductCreateRequest
import com.youngjinguy.product.model.ProductSearchRequest
import com.youngjinguy.product.model.ProductSearchResponse
import com.youngjinguy.product.model.ProductUpdateRequest
import com.youngjinguy.product.service.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

/**
 * @author Youngjin Kim
 * @since 2021-07-06
 */
@RestController
@RequestMapping("/products")
class ProductRestController(
    private val productService: ProductService
) {

    @PostMapping("")
    fun create(@RequestBody request: ProductCreateRequest): Mono<ResponseEntity<Long>> {
        return Mono.just(
            ResponseEntity.ok(productService.create(request))
        )
    }

    @PutMapping("/{no}")
    fun update(
        @RequestBody request: ProductUpdateRequest,
        @PathVariable("no") productNo: Long
    ): Mono<ResponseEntity<Void>> {
        productService.update(productNo = productNo, request = request)
        return Mono.just(
            ResponseEntity.noContent().build()
        )
    }

    @DeleteMapping("/{no}")
    fun delete(@PathVariable("no") productNo: Long): Mono<ResponseEntity<Void>> {
        productService.delete(productNo = productNo)
        return Mono.just(
            ResponseEntity.ok().build()
        )
    }

    @GetMapping("")
    fun search(request: ProductSearchRequest): Mono<ResponseEntity<ProductSearchResponse>> {
        return Mono.just(
            ResponseEntity.ok(productService.search(request))
        )
    }
}
