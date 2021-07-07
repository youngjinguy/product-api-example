package com.youngjinguy.product.service

import com.youngjinguy.product.entity.Option
import com.youngjinguy.product.entity.Product
import com.youngjinguy.product.entity.ProductCategoryRelation
import com.youngjinguy.product.model.ProductCreateRequest
import com.youngjinguy.product.model.ProductUpdateRequest
import com.youngjinguy.product.repository.ProductCategoryRelationRepository
import com.youngjinguy.product.repository.ProductRepository
import org.springframework.data.repository.findByIdOrNull
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

    @Transactional
    fun update(productNo: Long, request: ProductUpdateRequest): Long {
        updateProduct(productNo = productNo, request = request)
        updateProductCategoryRelation(productNo = productNo, categoryNos = request.categoryNos)
        return productNo
    }

    private fun updateProduct(productNo: Long, request: ProductUpdateRequest) {
        val product = productRepository.findByIdOrNull(productNo)
        product?.update(request)
    }

    private fun updateProductCategoryRelation(productNo: Long, categoryNos: Set<Long>) {
        if (categoryNos.isNotEmpty()) {
            val productCategoryRelation = productCategoryRelationRepository.findByProductNo(productNo)
            productCategoryRelationRepository.deleteAll(productCategoryRelation)
            addProductCategoryRelation(productNo = productNo, categoryNos = categoryNos)
        }
    }

    fun delete(productNo: Long) {
        val product = productRepository.findByIdOrNull(productNo)
        product?.delete()
    }

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
