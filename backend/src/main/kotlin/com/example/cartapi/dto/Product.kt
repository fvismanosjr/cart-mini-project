package com.example.cartapi.dto

import java.math.BigDecimal

data class ProductResponse(
    val id: Long,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val stock: Int
)