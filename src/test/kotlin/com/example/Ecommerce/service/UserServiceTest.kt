package com.example.Ecommerce.service

import com.example.Ecommerce.model.User
import com.example.Ecommerce.repository.UserRepository
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

class UserServiceTest {

    val user1 = User("12","Aman","123456789","789456123")
    val user2 = User("13","Shubham","123456780","789456121")

    private val userRepository = mockk<UserRepository>() {

        every {
            save(user1)
        } returns Mono.just(user1)

        every {
            findAll()
        } returns Flux.just(user1, user2)

        every {
            findById("13")
        }returns Mono.just(user2)

        every {
            deleteById("12")
        }
    }

    private val userService = UserService(userRepository)

    @Test
    fun `should return users when find method is called`(){
    val firstUser = userService.findAllusers().blockFirst()
        val secondUser = userService.findAllusers().blockFirst()

        if (firstUser != null) {
            firstUser shouldBe user1
        }
        if (secondUser != null) {
            secondUser shouldBe user2
        }

    }
//    @Test
//    fun `should expect on complete call post all the users`(){
//        //StepVerifier takes care of subscribing
//        StepVerifier.create(userService.findAllUsers()).expectSubscription().expectNext(user1).expectNext(user2)
//            .verifyComplete()
//        StepVerifier.create(userService.findAllUsers()).expectNextCount(2).verifyComplete()
//    }
}
