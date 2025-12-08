package com.example.cartapi.service

import com.example.cartapi.dto.LoginRequest
import com.example.cartapi.dto.RegisterRequest
import com.example.cartapi.dto.UserResponse
import com.example.cartapi.entity.Cart
import com.example.cartapi.entity.User
import com.example.cartapi.repository.CartRepository
import com.example.cartapi.repository.UserRepository
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val cartRepository: CartRepository,
    private val jwtService: JwtService
) {
    fun registerUser(request: RegisterRequest): String {
        userRepository
            .findByUsername(request.username.lowercase())
            ?.let { throw Exception("user already registered") }

        val user = userRepository.save(
            User(
                username = request.username.lowercase(),
                password = BCryptPasswordEncoder().encode(request.password),
                role = "ROLE_USER"
            )
        )

        cartRepository.save(
            Cart(
                user = user
            )
        )

        return "User registered successfully"
    }

    fun authenticateUser(request: LoginRequest, response: HttpServletResponse): UserResponse {
        val user = request
            .let { userRepository.findByUsername(it.username) ?: throw Exception("user not found") }
            .takeIf { BCryptPasswordEncoder().matches(request.password, it.password) }
            ?: throw BadCredentialsException("Invalid credentials")

        // process cookie
        response.addCookie(jwtService.generateCookie(
            token = jwtService.generateToken(user.username)
        ))

        return user.toResponse()
    }

    fun unAuthenticateUser(response: HttpServletResponse)
    {
        jwtService.clearCookie(response)
    }
}