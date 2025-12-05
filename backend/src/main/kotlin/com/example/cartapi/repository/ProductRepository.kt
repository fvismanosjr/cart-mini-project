package com.example.cartapi.repository

import com.example.cartapi.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    fun findByIdAndStockGreaterThan(productId: Long, stock: Int = 0): Product?
}