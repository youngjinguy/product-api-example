package com.youngjinguy.product.entity

import com.youngjinguy.product.enums.SaleStatus
import com.youngjinguy.product.model.ProductCreateRequest
import com.youngjinguy.product.model.ProductUpdateRequest
import org.hibernate.annotations.DynamicUpdate
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

/**
 * @author Youngjin Kim
 * @since 2021-07-06
 */
@Entity
@Table(name = "pd_product2")
@DynamicUpdate
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val no: Long = 0,
    @Column(length = 200)
    val name: String,
    val adminNo: Long,
    val price: BigDecimal = BigDecimal.ZERO,
    val saleStartYmdt: LocalDateTime = LocalDateTime.now(),
    val saleEndYmdt: LocalDateTime,
    val saleStatus: SaleStatus,
    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL])
    val options: MutableList<Option> = mutableListOf(),
    val representCategoryNo: Long,
    val deleted: Boolean,
    val registerYmdt: LocalDateTime
) {
    private fun addOption(option: Option) {
        this.options.add(option)
        option.setProduct(this)
    }

    fun setOptions(options: List<Option>) {
        options.forEach { this.addOption(it) }
    }

    fun update(request: ProductUpdateRequest) {
        request.name?.let { this.copy(name = it) }
        request.adminNo?.let { this.copy(adminNo = it) }
        request.price?.let { this.copy(price = it) }
        request.saleStartYmdt?.let { this.copy(saleStartYmdt = it) }
        request.saleEndYmdt?.let { this.copy(saleEndYmdt = it) }
        request.representCategoryNo?.let { this.copy(representCategoryNo = it) }
        val options = request.options
        if (options.isNotEmpty()) {
            options.forEach { request ->
                val option = this.options.first { it.no == request.no }
                option.update(request)
            }
        }
    }

    companion object {
        fun createBy(request: ProductCreateRequest): Product {
            return Product(
                name = request.name,
                adminNo = request.adminNo,
                price = request.price,
                saleStartYmdt = request.saleStartYmdt,
                saleEndYmdt = request.saleEndYmdt,
                saleStatus = SaleStatus.NORMAL,
                representCategoryNo = request.representCategoryNo,
                deleted = false,
                registerYmdt = LocalDateTime.now()
            )
        }
    }
}
