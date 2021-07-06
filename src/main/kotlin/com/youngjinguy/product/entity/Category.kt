package com.youngjinguy.product.entity

import javax.persistence.*

/**
 * @author Youngjin Kim
 * @since 2021-07-06
 */
@Entity
@Table(name = "pd_category")
class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_no")
    val no: Long,

    @Column(name = "category_name")
    val name: String
)
