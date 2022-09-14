package com.example.Ecommerce.repository

import com.example.Ecommerce.model.User
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UserRepository : ReactiveMongoRepository<User, Int> {

    @Query("{'name':?0}")
    fun findByName(name:String): Mono<User>
    abstract fun findById(id: String): Mono<User>
    fun deleteById(id: String): Mono<Void>
}