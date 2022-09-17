package com.example.Ecommerce.controller

import com.example.Ecommerce.model.Product
import com.example.Ecommerce.model.User
import com.example.Ecommerce.repository.ProductRepository
import com.example.Ecommerce.repository.UserRepository
import com.example.Ecommerce.service.ProductService
import com.example.Ecommerce.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@CrossOrigin("*")

@RestController
//@RequestMapping("v1")
class ProductController (
    @Autowired
    //val productRepository: ProductRepository,
    val productService: ProductService,
    ){

//        @GetMapping("/products/list")
//        fun getAllProducts(): Flux<Product> {
//            return productService.findAllproducts()
//        }

//    @GetMapping("/products/{id}")
//    fun getUserById(@PathVariable id: String): Mono<Product> {
//        return productService.findById(id)
//    }
    @PostMapping("/products/add")
    fun save(@RequestBody product: Product): Mono<Product> {
        return productService.addProduct(product)
    }
//    @PutMapping("/update/{id}")
//    fun updateById(@PathVariable("id") id: String, @RequestBody product: Product): Mono<Product> {
//        return productService.updateProduct(id,product)
//    }

    @PutMapping("/updateProduct/{id}")
    fun update(@PathVariable("id") id: String,@RequestBody product: Product): Mono<Product> {
        return productService.updateProductById(id,product)
    }
    @DeleteMapping("/products/{id}")
    fun deleteUser(@PathVariable id: String): Mono<Void> {
        return productService.deleteById(id)
    }
}

