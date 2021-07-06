package com.youngjinguy.product.model

import java.math.BigDecimal

/**
 * @author Youngjin Kim
 * @since 2021-07-06
 */
data class OptionCreateRequest(
    val name: String,
    val price: BigDecimal,
    val representative: Boolean
)

