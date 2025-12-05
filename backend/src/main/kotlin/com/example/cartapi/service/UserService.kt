package com.example.cartapi.service

import com.example.cartapi.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun findAll() = userRepository.findAll().map { it.toResponse() }
}