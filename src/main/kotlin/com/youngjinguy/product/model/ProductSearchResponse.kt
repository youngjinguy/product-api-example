package com.youngjinguy.product.model

/**
 * @author Youngjin Kim
 * @since 2021-07-07
 */
data class ProductSearchResponse(
    val contents: List<SearchProduct>,
    val totalCount: Long
)
