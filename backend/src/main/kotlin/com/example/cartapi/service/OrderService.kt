package com.example.cartapi.service

import com.example.cartapi.dto.OrderRequest
import com.example.cartapi.dto.OrderResponse
import com.example.cartapi.entity.Order
import com.example.cartapi.entity.OrderItem
import com.example.cartapi.repository.CartRepository
import com.example.cartapi.repository.OrderRepository
import com.example.cartapi.repository.ProductRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDateTime

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val cartRepository: CartRepository,
    private val productRepository: ProductRepository,
) {
    fun save(request: OrderRequest): OrderResponse {
        var total = BigDecimal(0.00);
        val cart = cartRepository.findById(request.cartId).orElseThrow()

        // save order
        val order = orderRepository.save(
            Order(
                user = cart.user,
                status = "processing",
                totalAmount = total,
                createdAt = LocalDateTime.now(),
            )
        )

        // save order items
        request.items.forEach { item ->
            productRepository
            .findByIdAndStockGreaterThan(item.productId)
            ?.let {
                order.orderItems.add(
                    OrderItem(
                        product = it,
                        quantity = item.quantity,
                        purchasePrice = item.price,
                        order = order
                    )
                )

                total += item.price
            } ?: println("product is out of stock or missing")
        }

        order.totalAmount = total
        orderRepository.save(order)

        // clear cart items
        cart.cartItems.clear()
        cartRepository.save(cart)

        return order.toResponse()
    }
}