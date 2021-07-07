package com.youngjinguy.product.enums

import java.time.LocalDateTime

/**
 * @author Youngjin Kim
 * @since 2021-07-07
 */
enum class SaleStatusSearchType(
    val saleStatus: SaleStatus?,
    val deleted: Boolean
) {
    // 판매시작일 이전
    WAITING(SaleStatus.NORMAL, false),

    // 판매시작일과 판매종료일 사이
    SALE(SaleStatus.NORMAL, false),

    // 판매종료일 이후
    FINISH(SaleStatus.NORMAL, false),

    // 시점무관
    STOP(SaleStatus.STOP, false),

    // 시점무관
    PROHIBITION(SaleStatus.PROHIBITION, false),

    // 시점무관
    DELETE(null, true);

    companion object {
        fun createBy(
            saleStatus: SaleStatus,
            saleStartYmdt: LocalDateTime,
            saleEndYmdt: LocalDateTime,
            deleted: Boolean
        ): SaleStatusSearchType {
            if (deleted) return DELETE
            return when (saleStatus) {
                SaleStatus.NORMAL -> {
                    val now = LocalDateTime.now()
                    if (saleStartYmdt.isBefore(now)) {
                        WAITING
                    } else if (saleStartYmdt.isAfter(now) && now.isBefore(saleEndYmdt)) {
                        SALE
                    } else {
                        FINISH
                    }
                }
                SaleStatus.STOP -> STOP
                SaleStatus.PROHIBITION -> PROHIBITION
            }
        }
    }
}
