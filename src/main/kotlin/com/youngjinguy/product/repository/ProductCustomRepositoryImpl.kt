package com.youngjinguy.product.repository

import com.querydsl.core.BooleanBuilder
import com.youngjinguy.product.configuration.KotlinQuerydslRepositorySupport
import com.youngjinguy.product.entity.Product
import com.youngjinguy.product.entity.QProduct
import com.youngjinguy.product.entity.QProductCategoryRelation
import com.youngjinguy.product.enums.SaleStatusSearchType
import com.youngjinguy.product.model.ProductSearchRequest
import com.youngjinguy.product.model.SearchProduct
import org.springframework.stereotype.Repository

/**
 * @author Youngjin Kim
 * @since 2021-07-07
 */
@Repository
class ProductCustomRepositoryImpl : ProductCustomRepository, KotlinQuerydslRepositorySupport(Product::class.java) {
    private val product = QProduct.product
    private val relation = QProductCategoryRelation.productCategoryRelation
    override fun searchProducts(request: ProductSearchRequest): List<SearchProduct> {
        val query = from(product).innerJoin(relation).on(
            product.no.eq(relation.productNo)
        ).where(
            createWhereClause(request)
        )

        return querydsl.applyPagination(request.pager(), query).fetch().map {
            SearchProduct.createBy(it)
        }
    }

    override fun searchProductsCount(request: ProductSearchRequest): Long {
        return from(product).innerJoin(relation).on(
            product.no.eq(relation.productNo)
        ).where(createWhereClause(request)).fetchCount()
    }

    private fun createWhereClause(request: ProductSearchRequest): BooleanBuilder {
        return BooleanBuilder().apply {
            if (request.productNos.isNotEmpty()) {
                this.and(product.no.`in`(request.productNos))
            }
            if (request.categoryNo > 0) {
                this.and(relation.categoryNo.eq(request.categoryNo))
            }
            if (request.saleStatusSearchType.isNotEmpty()) {
                val saleStatusList = request.saleStatusSearchType.mapNotNull { it.saleStatus }
                this.and(product.saleStatus.`in`(saleStatusList))
                if (request.saleStatusSearchType.contains(SaleStatusSearchType.DELETE)) {
                    this.and(product.deleted.isTrue)
                }
            }

        }
    }
}
