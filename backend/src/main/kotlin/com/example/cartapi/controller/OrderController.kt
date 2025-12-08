package com.example.cartapi.controller

import com.example.cartapi.dto.OrderRequest
import com.example.cartapi.service.OrderService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@Validated
@RequestMapping("/orders")
class OrderController(
    private val orderService: OrderService,
) {
    @GetMapping
    fun findAll() {}

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun store(
        @Valid @RequestBody request: OrderRequest
    ) = orderService.save(request)
}