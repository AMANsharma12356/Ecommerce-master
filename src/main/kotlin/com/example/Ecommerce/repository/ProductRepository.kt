package com.example.Ecommerce.repository

import com.example.Ecommerce.model.Product
import com.example.Ecommerce.model.User
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface ProductRepository : ReactiveCrudRepository<Product, Int> {
    fun deleteById(id: String): Mono<Void>
    fun findById(id: String): Mono<Product>


}