package com.example.cartapi.service

import com.example.cartapi.dto.CartItemRequest
import com.example.cartapi.dto.CartItemResponse
import com.example.cartapi.entity.CartItem
import com.example.cartapi.repository.CartItemRepository
import com.example.cartapi.repository.CartRepository
import com.example.cartapi.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class CartItemService(
    private val cartRepository: CartRepository,
    private val cartItemRepository: CartItemRepository,
    private val productRepository: ProductRepository,
) {
    fun save(
        cartId: Long,
        request: CartItemRequest
    ): CartItemResponse {
        // check if cart exists
        val cart = cartRepository.findById(cartId).orElseThrow()

        // find if product exists and stock is greater than 0
        val product = productRepository
                    .findByIdAndStockGreaterThan(request.productId)
                    ?: throw Exception("product unavailable")


        return cartItemRepository.save(
            CartItem(
                cart = cart,
                product = product,
                quantity = request.quantity
            )
        ).toResponse()
    }

    fun update(
        cartId: Long,
        cartItemId: Long,
        request: CartItemRequest
    ): CartItemResponse {
        // check if cart exists
        cartRepository.findById(cartId).orElseThrow()

        // check if cart item exist
        val cartItem = cartItemRepository.findById(cartItemId).orElseThrow()

        // find if product exists and stock is greater than 0
        productRepository
        .findByIdAndStockGreaterThan(request.productId)
        ?: throw Exception("product unavailable")

        cartItem.quantity = request.quantity
        return cartItemRepository.save(cartItem).toResponse()
    }

    fun delete(
        cartId: Long,
        cartItemId: Long
    ) {
        // check if cart exists
        cartRepository.findById(cartId).orElseThrow()

        // check if cart item exist
        val cartItem = cartItemRepository.findById(cartItemId).orElseThrow()

        cartItemRepository.delete(cartItem)
    }
}