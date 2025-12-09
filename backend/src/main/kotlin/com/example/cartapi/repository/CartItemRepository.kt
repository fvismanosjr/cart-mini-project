package com.example.cartapi.repository

import com.example.cartapi.entity.CartItem
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface CartItemRepository: JpaRepository<CartItem, Long>, JpaSpecificationExecutor<CartItem> {
    fun findByCartId(cartId: Long, pageable: Pageable): Page<CartItem>
}