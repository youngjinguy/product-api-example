package com.youngjinguy.product.model

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

/**
 * @author Youngjin Kim
 * @since 2021-07-07
 */
data class Pager(
    private val page: Int = 1,
    private val size: Int = 10,
    private val sort: Sort = Sort.unsorted()
) : Pageable {
    override fun getPageNumber(): Int = page

    override fun hasPrevious(): Boolean = page > 1

    override fun getSort(): Sort = sort

    override fun next(): Pageable = Pager(page + 1, size)

    override fun getPageSize(): Int = size

    override fun getOffset(): Long = getPageSize().toLong() * (page.toLong() - 1L)

    override fun first(): Pageable = Pager(1, size)
    override fun withPage(pageNumber: Int): Pageable = Pager(page, size)

    override fun previousOrFirst(): Pageable = if (page <= 1) first() else Pager(
        page - 1,
        size
    )
}
