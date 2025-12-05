package com.example.cartapi.repository

import com.example.cartapi.entity.CartItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface CartItemRepository: JpaRepository<CartItem, Long>, JpaSpecificationExecutor<CartItem>