package com.example.Ecommerce.service

import com.example.Ecommerce.model.Product
import com.example.Ecommerce.model.User
import com.example.Ecommerce.repository.ProductRepository
import com.example.Ecommerce.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ProductService(
    @Autowired
    val productRepository: ProductRepository
) {
//    fun findAllproducts(): Flux<Product> {
//        return productRepository.findAll()
//    }

    fun addProduct(product: Product): Mono<Product> {
        return productRepository.save(product)

    }

    fun updateProductById(id:String, product: Product): Mono<Product> {
        return productRepository.save(product)
    }

    fun deleteById(id: String): Mono<Void> {
        return productRepository.deleteById(id)
    }

//    fun findById(id: String): Mono<Product> {
//        return productRepository.findById(id)
//    }

//    companion object {
//        fun addProduct(product: Any): Mono<Product> {
//            TODO("Not yet implemented")
        }



