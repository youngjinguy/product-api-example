package com.youngjinguy.product.repository

import com.youngjinguy.product.entity.ProductCategoryRelation
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author Youngjin Kim
 * @since 2021-07-06
 */
interface ProductCategoryRelationRepository : JpaRepository<ProductCategoryRelation, Long> {
    fun findByProductNo(productNo: Long): List<ProductCategoryRelation>
}
