package com.example.cartapi.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class OrderItemRequest(
    val productId: Long,
    val quantity: Int,
    val price: BigDecimal,
)

data class OrderRequest(
    val cartId: Long,
    val items: List<OrderItemRequest>
)

data class OrderResponse(
    val id: Long,
    val items: List<OrderItemResponse>,
    val status: String,
    val totalAmount: BigDecimal,
    val createdAt: LocalDateTime,
)

data class OrderItemResponse(
    val id: Long,
    val product: ProductResponse,
    val quantity: Int,
    val price: BigDecimal,
)

data class CancelOrderRequest(
    val status: String,
)