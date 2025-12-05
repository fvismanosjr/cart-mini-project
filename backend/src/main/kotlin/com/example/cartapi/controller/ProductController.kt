package com.example.cartapi.controller

import com.example.cartapi.repository.ProductRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductController(
    private val productRepository: ProductRepository
) {
    @GetMapping
    fun findAll() = productRepository.findAll().map { it.toResponse() }
}