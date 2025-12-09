package com.example.cartapi.controller

import com.example.cartapi.service.CartItemService
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users/{userId}/carts/{cartId}/items")
class CartItemController(
    private val cartItemService: CartItemService,
) {
    @GetMapping
    fun findAllByCartId(
        @PathVariable userId: Long,
        @PathVariable cartId: Long,
        @PageableDefault(
            page = 0,
            size = 20,
            sort = ["id"],
            direction = Sort.Direction.ASC
        ) pageable: Pageable? = Pageable.unpaged()
    ) = cartItemService.findAllByCartId(cartId, pageable)
}