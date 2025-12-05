package com.example.cartapi.entity

import com.example.cartapi.dto.CartResponse
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.engine.internal.Cascade
import java.math.BigDecimal

@Entity
@Table(name = "carts")
class Cart (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    var user: User,

    @OneToMany(
        mappedBy = "cart",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var cartItems: MutableList<CartItem> = mutableListOf()
) {
    fun toResponse(): CartResponse {
        return CartResponse(
            this.id,
            items = this.cartItems.map { it.toResponse() }
        )
    }
}