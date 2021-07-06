package com.youngjinguy.product.entity

import com.youngjinguy.product.model.OptionCreateRequest
import java.math.BigDecimal
import javax.persistence.*

/**
 * @author Youngjin Kim
 * @since 2021-07-06
 */
@Entity
@Table(name = "pd_option2")
class Option(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val no: Long = 0,
    @Column(length = 100)
    val name: String,
    val price: BigDecimal,
    val representative: Boolean
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productNo", insertable = false, updatable = false)
    var product: Product? = null
        private set

    val salePrice: BigDecimal
        get() {
            return this.product!!.price + this.price
        }

    fun setProduct(product: Product) {
        this.product = product
    }

    companion object {
        fun createBy(request: OptionCreateRequest): Option {
            return Option(
                name = request.name,
                price = request.price,
                representative = request.representative
            )
        }
    }
}
