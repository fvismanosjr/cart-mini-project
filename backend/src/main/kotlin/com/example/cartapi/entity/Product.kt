package com.example.cartapi.entity

import com.example.cartapi.dto.ProductResponse
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "products")
class Product (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "name")
    var name: String,

    @Column(name = "description", columnDefinition = "TEXT")
    var description: String,

    @Column(name = "price", precision = 10, scale = 2)
    var price: BigDecimal,

    @Column(name = "stock")
    var stock: Int = 0,
) {
    fun toResponse(): ProductResponse {
        return ProductResponse(
            this.id,
            this.name,
            this.description,
            this.price,
            this.stock
        )
    }
}