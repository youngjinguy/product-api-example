package com.youngjinguy.product.model

import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * @author Youngjin Kim
 * @since 2021-07-06
 */
data class ProductCreateRequest(
    val name: String,
    val adminNo: Long,
    val price: BigDecimal,
    val saleStartYmdt: LocalDateTime,
    val saleEndYmdt: LocalDateTime,
    val options: List<OptionCreateRequest>,
    val representCategoryNo: Long,
    val categoryNos: Set<Long>
)
