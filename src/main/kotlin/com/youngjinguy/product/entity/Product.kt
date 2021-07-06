package com.youngjinguy.product.entity

import com.youngjinguy.product.enums.SaleStatus
import com.youngjinguy.product.model.ProductCreateRequest
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

/**
 * @author Youngjin Kim
 * @since 2021-07-06
 */
@Entity
@Table(name = "pd_product2")
class Product(
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
