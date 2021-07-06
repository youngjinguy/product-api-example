package com.youngjinguy.product.service

import com.youngjinguy.product.entity.Option
import com.youngjinguy.product.entity.Product
import com.youngjinguy.product.entity.ProductCategoryRelation
import com.youngjinguy.product.model.ProductCreateRequest
import com.youngjinguy.product.repository.ProductCategoryRelationRepository
import com.youngjinguy.product.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author Youngjin Kim
 * @since 2021-07-06
 */
@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val productCategoryRelationRepository: ProductCategoryRelationRepository
) {
    @Transactional
    fun create(request: ProductCreateRequest): Long {
        val productNo = addProduct(request)
        addProductCategoryRelation(productNo = productNo, categoryNos = request.categoryNos)
        return productNo
    }

    fun update() {
    }

    fun delete() {
    }

    //    fun findById(productNo: Long): Product {
    //    }
    //
    //    fun findByAll(): List<Product> {
    //    }

    private fun addProduct(request: ProductCreateRequest): Long {
        val product = Product.createBy(request)
        val options = request.options.map {
            Option.createBy(it)
        }
        product.setOptions(options)
        productRepository.save(product)
        return product.no
    }

    private fun addProductCategoryRelation(productNo: Long, categoryNos: Set<Long>) {
        val productCategoryRelations = categoryNos.map {
            ProductCategoryRelation(
                productNo = productNo,
                categoryNo = it
            )
        }
        productCategoryRelationRepository.saveAll(productCategoryRelations)
    }
}
