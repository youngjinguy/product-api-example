package com.youngjinguy.product.repository

import com.youngjinguy.product.model.ProductSearchRequest
import com.youngjinguy.product.model.SearchProduct

/**
 * @author Youngjin Kim
 * @since 2021-07-07
 */
interface ProductCustomRepository {
    fun searchProducts(request: ProductSearchRequest): List<SearchProduct>
    fun searchProductsCount(request: ProductSearchRequest): Long
}
