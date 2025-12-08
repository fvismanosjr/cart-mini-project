package com.example.cartapi.controller

import com.example.cartapi.dto.CancelOrderRequest
import com.example.cartapi.dto.CartItemRequest
import com.example.cartapi.dto.OrderRequest
import com.example.cartapi.service.CartItemService
import com.example.cartapi.service.OrderService
import com.example.cartapi.service.UserService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Validated
@RequestMapping("/users")
class UserController(
    private val userService: UserService,
    private val cartItemService: CartItemService,
    private val orderService: OrderService,
) {
    @GetMapping("/{userId}")
    fun find(
        @PathVariable userId: Long,
    ) = userService.find(userId)

    @PostMapping("/{userId}/carts/{cartId}/items")
    fun addToCart(
        @PathVariable userId: Long,
        @PathVariable cartId: Long,
        @RequestBody request: CartItemRequest
    ) = cartItemService.save(cartId, request)

    @DeleteMapping("/{userId}/carts/{cartId}/items/{itemId}")
    fun removeFromCart(
        @PathVariable userId: Long,
        @PathVariable cartId: Long,
        @PathVariable itemId: Long,
    ) = cartItemService.delete(cartId, itemId)

    @PostMapping("/{userId}/orders")
    fun addToOrder(
        @PathVariable userId: Long,
        @RequestBody request: OrderRequest
    ) = orderService.save(userId, request)

    @PatchMapping("/{userId}/orders/{orderId}")
    fun cancelOrder(
        @PathVariable userId: Long,
        @PathVariable orderId: Long,
        @RequestBody request: CancelOrderRequest
    ) = orderService.cancel(orderId, request)
}