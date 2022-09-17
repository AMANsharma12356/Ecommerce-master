package com.example.Ecommerce.repository

import com.example.Ecommerce.model.Product
import com.example.Ecommerce.model.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface ProductRepository : ReactiveMongoRepository<Product, String> {



}