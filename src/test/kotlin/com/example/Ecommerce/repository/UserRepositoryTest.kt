package com.example.Ecommerce.repository

import com.example.Ecommerce.model.User
import io.kotlintest.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.test.context.ActiveProfiles

@DataMongoTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    lateinit var userRepository: UserRepository

    @BeforeEach
    fun tearDown(){
        userRepository.deleteAll().subscribe()
    }
    @Test
    fun `should find user for id`(){
        val user = User("12","Aman","123456789","789456123")

        userRepository.save(user).block()

        val actualSaveApplication = userRepository.findById("12").block()

        actualSaveApplication shouldBe  user
    }
}