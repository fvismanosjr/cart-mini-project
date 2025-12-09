package com.example.cartapi.dto

import jakarta.validation.constraints.Min

data class CartItemRequest(
    @field:Min(1)
    val productId: Long,

    @field:Min(1)
    val quantity: Int,
)

data class UpdateCartItemRequest(
    @field:Min(1)
    val quantity: Int
)

data class CartResponse(
    val id: Long,
    val items: List<CartItemResponse>
)

data class CartItemResponse(
    val id: Long,
    val product: ProductResponse,
    val quantity: Int,
)