package com.example.cartapi.repository

import com.example.cartapi.entity.Cart
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface CartRepository: JpaRepository<Cart, Long>, JpaSpecificationExecutor<Cart>