package com.example.cartapi.service

import com.example.cartapi.dto.CartResponse
import com.example.cartapi.entity.Cart
import com.example.cartapi.repository.CartRepository
import org.springframework.stereotype.Service

@Service
class CartService(
    private val cartRepository: CartRepository
) {
    fun find(
        id: Long
    ): CartResponse = cartRepository
                        .findById(id)
                        .map { it.toResponse() }
                        .orElseThrow()
}