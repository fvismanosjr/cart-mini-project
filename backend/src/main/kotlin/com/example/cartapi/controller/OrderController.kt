package com.example.cartapi.controller

import com.example.cartapi.service.OrderService
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users/{userId}/orders")
class OrderController(
    private val orderService: OrderService
) {
    @GetMapping
    fun findAllByUserId(
        @PathVariable userId: Long,
        @PageableDefault(
            page = 0,
            size = 20,
            sort = ["id"],
            direction = Sort.Direction.ASC
        ) pageable: Pageable? = Pageable.unpaged()
    ) = orderService.findAllByUserId(userId, pageable)
}