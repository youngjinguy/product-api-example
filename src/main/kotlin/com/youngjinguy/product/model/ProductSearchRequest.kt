package com.youngjinguy.product.model

import com.youngjinguy.product.enums.SaleStatusSearchType

/**
 * @author Youngjin Kim
 * @since 2021-07-07
 */
data class ProductSearchRequest(
    val productNos: Set<Long> = emptySet(),
    val categoryNo: Long = 0,
    val saleStatusSearchType: Set<SaleStatusSearchType> = emptySet(),
    val page: Int = 1,
    val offset: Int = 1,
    val limit: Int = 10,
) {
    fun pager() = Pager(
        page = this.page,
        size = this.limit
    )
}
