package com.example.Ecommerce.controller

import com.example.Ecommerce.model.User
import com.example.Ecommerce.repository.UserRepository
import com.example.Ecommerce.service.UserService
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.returnResult
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@WebFluxTest(UserController::class)
@AutoConfigureWebTestClient
class UserControllerTest {

    @Autowired
    lateinit var client: WebTestClient

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var userRepository: UserRepository

    @TestConfiguration
    class ControllerTestConfig {
        @Bean
        fun userService() = mockk<UserService>()

        @Bean
        fun userRepository() = mockk<UserRepository>()

    }

    @Test
//arranging the data member
    fun `should return list  `() {
        val user = User(
            "12", "Aman", "123456789", "789456123"
        )

        //expectations
        val expectedResult = mapOf(
            "userId" to "12",
            "userName" to "Aman",
            "userContactno" to "123456789",
            "userPassword" to "789456123"
        )

        //action - invoking the methods
        every {
                    userService.findAllusers()
                } returns Flux.just(user)

        //Assertions
        val response = client.get()
            .uri("/users/list")
            .accept(MediaType.APPLICATION_JSON)
            .exchange() //invoking the end point
            .expectStatus().is2xxSuccessful
            .returnResult<Any>()
            .responseBody

        response.blockFirst() shouldBe expectedResult

        verify(exactly = 1) {
            userService.findAllusers()
        }
    }
    //dealing with Api's
//as a user i want to retrieve all the users from the database
    //Api -
            @Test
            fun `should return one user`() {

                val user = User(
                    "12", "Aman", "123456789", "789456123"
                )

                val expectedResult = mapOf(
                    "userId" to "12",
                    "userName" to "Aman",
                    "userContactno" to "123456789",
                    "userPassword" to "789456123"
                )
                every {
                    userService.findById("1")
                } returns Mono.just(user)
                val response = client.get()
                    .uri("/users/1")
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange() //invoking the end point
                    .expectStatus().is2xxSuccessful
                    .returnResult<Any>()
                    .responseBody

                response.blockFirst() shouldBe expectedResult

                verify(exactly = 1) {
                    userService.findById("1")
                }
            }

   @Test
    fun `should create user when api is being called`(){

        val expectedResponse = mapOf(
            "userId" to "12",
            "userName" to "Aman",
            "userContactno" to "123456789",
            "userPassword" to "789456123"
        )
        val user = User(
            "12", "Aman", "123456789", "789456123"
        )

        every {
            userService.addUser(user)
        } returns Mono.just(user)

        val response = client.post()
            .uri("/users/add")
            .bodyValue(user)
            .exchange()
            .expectStatus().is2xxSuccessful
            .returnResult<Any>().responseBody

        response.blockFirst() shouldBe expectedResponse

        verify(exactly = 1){
            userService.addUser(user)
        }
    }
    @Test
    fun `should able to delete user`() {
        val expectedResponse = mapOf(
            "userId" to "12",
            "userName" to "Aman",
            "userContactno" to "123456789",
            "userPassword" to "789456123"
        )
        val user = User(
            "12", "Aman", "123456789", "789456123"
        )
        every {
            userService.deleteById("1")
        } returns Mono.empty()
        val response = client.delete()
            .uri("/users/1")
            .exchange()
            .expectStatus().is2xxSuccessful

        verify(exactly = 1) {
            userService.deleteById("1")
        }
    }

        }
