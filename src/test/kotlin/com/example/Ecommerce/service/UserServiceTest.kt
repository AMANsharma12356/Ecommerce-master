package com.example.Ecommerce.service

import com.example.Ecommerce.model.User
import com.example.Ecommerce.repository.UserRepository
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.reactor.flux
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class UserServiceTest {

    //mocking the repository layer response

    val user = User(
        "12", "Aman", "123456789", "789456123"
    )
    val user1 = User(
        "13", "sharma", "1203654789", "789456123"
    )
    private val userRepository = mockk<UserRepository>() {
        every {
            findAll()
        } returns Flux.just(user, user1)
        every {
            findById("1")
        } returns Mono.just(user1)
    }
    private val userService = UserService(userRepository)

    @Test
    fun `should return users when find all method is called`() {
        val User = userService.findAllusers().blockFirst()
        val firstUser  = userService.findAllusers().blockLast()

        User shouldBe user
        firstUser shouldBe user1
    }
    @Test
    fun `should find the list of users on the basis of the id`() {

        val result = userService.findById("1").block()

        result shouldBe user
    }

}




