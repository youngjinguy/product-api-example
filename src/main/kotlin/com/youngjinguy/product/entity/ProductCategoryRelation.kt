package com.youngjinguy.product.entity

import javax.persistence.*

/**
 * @author Youngjin Kim
 * @since 2021-07-06
 */
@Entity
@Table(name = "pd_product_category_relation2")
class ProductCategoryRelation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val seq: Long = 0,
    val productNo: Long,
    val categoryNo: Long
)
