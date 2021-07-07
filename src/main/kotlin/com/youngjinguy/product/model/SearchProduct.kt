package com.youngjinguy.product.model

import com.youngjinguy.product.entity.Product
import com.youngjinguy.product.enums.SaleStatusSearchType
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * @author Youngjin Kim
 * @since 2021-07-07
 */
data class SearchProduct(
    val no: Long,
    val name: String,
    val price: BigDecimal,
    val saleStartYmdt: LocalDateTime,
    val saleEndYmdt: LocalDateTime,
    val saleStatusSearchType: SaleStatusSearchType,
    val representOptionCount: Int,
    val optionCount: Int
) {
    fun saleYmdt(): String {
        return "${this.saleStartYmdt} ~ ${this.saleEndYmdt}"
    }

    companion object {
        fun createBy(product: Product): SearchProduct {
            return SearchProduct(
                no = product.no,
                name = product.name,
                price = product.price,
                saleStartYmdt = product.saleStartYmdt,
                saleEndYmdt = product.saleEndYmdt,
                saleStatusSearchType = SaleStatusSearchType.createBy(
                    saleStatus = product.saleStatus,
                    saleStartYmdt = product.saleStartYmdt,
                    saleEndYmdt = product.saleEndYmdt,
                    deleted = product.deleted
                ),
                representOptionCount = product.options.filter { it.representative }.count(),
                optionCount = product.options.size
            )
        }
    }
}
