package com.example.cartapi.entity

import com.example.cartapi.dto.OrderResponse
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
class Order (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,

    @Column(name = "total_amount", precision = 10, scale = 2)
    var totalAmount: BigDecimal,

    @Column(name = "status")
    var status: String,

    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @OneToMany(
        mappedBy = "order",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var orderItems: MutableList<OrderItem> = mutableListOf()
) {
    fun toResponse(): OrderResponse {
        return OrderResponse(
            this.id,
            this.orderItems.map {
                it.toResponse()
            },
            this.createdAt
        )
    }
}