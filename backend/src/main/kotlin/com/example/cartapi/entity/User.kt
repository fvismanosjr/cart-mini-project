package com.example.cartapi.entity

import com.example.cartapi.dto.UserResponse
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.OrderBy
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "username", nullable = false)
    var username: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "role")
    var role: String,

    @OneToOne(
        mappedBy = "user",
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY
    )
    var cart: Cart? = null,

    @OneToMany(
        mappedBy = "user",
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY
    )
    @OrderBy("id ASC")
    var orders: MutableList<Order> = mutableListOf()
) {
    fun toResponse(): UserResponse {
        return UserResponse(
            this.id,
            this.username,
            this.role,
            this.cart?.toResponse(),
            this.orders.map { it.toResponse() }
        )
    }
}