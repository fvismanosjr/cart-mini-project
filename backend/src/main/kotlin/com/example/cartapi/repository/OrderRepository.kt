package com.example.cartapi.repository

import com.example.cartapi.entity.Order
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository: JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    fun findAllByUserId(userId: Long, pageable: Pageable): Page<Order>
}