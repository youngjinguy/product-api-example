package com.youngjinguy.product.repository

import com.youngjinguy.product.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author Youngjin Kim
 * @since 2021-07-06
 */
interface ProductRepository : JpaRepository<Product, Long> {

}
