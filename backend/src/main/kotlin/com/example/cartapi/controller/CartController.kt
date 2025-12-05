package com.example.cartapi.controller

import com.example.cartapi.dto.CartItemRequest
import com.example.cartapi.service.CartItemService
import com.example.cartapi.service.CartService
import jakarta.validation.Valid
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Validated
@RequestMapping("/carts")
class CartController (
    private val cartService: CartService,
    private val cartItemService: CartItemService,
) {
    @GetMapping("/{id}")
    fun find(
        @PathVariable id: Long
    ) = cartService.find(id)

    @PostMapping("/{id}/items")
    fun storeItem(
        @PathVariable id: Long,
        @Valid @RequestBody request: CartItemRequest
    ) = cartItemService.save(id, request)

    @PutMapping("/{id}/items/{itemId}")
    fun updateItem(
        @PathVariable id: Long,
        @PathVariable itemId: Long,
        @Valid @RequestBody request: CartItemRequest
    ) = cartItemService.update(id, itemId, request)

    @DeleteMapping("/{id}/items/{itemId}")
    fun deleteItem(
        @PathVariable id: Long,
        @PathVariable itemId: Long,
    ) = cartItemService.delete(id, itemId)
}