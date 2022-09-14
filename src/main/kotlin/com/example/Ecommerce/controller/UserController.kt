package com.example.Ecommerce.controller

import com.example.Ecommerce.model.User
import com.example.Ecommerce.repository.UserRepository
import com.example.Ecommerce.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@CrossOrigin("*")

@RestController
//@RequestMapping("v1")
class UserController(

    @Autowired
    val userRepository: UserRepository,
    val userService: UserService
){

    @GetMapping("/users/list")
    fun getAllUsers(): Flux<User> {
        return userService.findAllusers()
    }

    @GetMapping("/users/{id}")
    fun getUserById(@PathVariable id: String): Mono<User> {
        return userService.findById(id)
    }

    @PostMapping("/users/add")
    fun save(@RequestBody user: User): Mono<User> {
        return UserService.addUser(user)
    }

    @PutMapping("/update/{id}")
    fun updateById(@PathVariable("id") id: String, @RequestBody user: User): Mono<User> {
        return userService.updateUser(user)
    }

//    @PutMapping("/updateUser")
//    fun update(@RequestBody user: User): Mono<User> {
//        return userService.updateUser(user)
//    }

    @DeleteMapping
    fun delete(): Mono<Void> {
        return userRepository.deleteAll()
    }


    @DeleteMapping("/users/{id}")
    fun deleteUser(@PathVariable id: String): Mono<Void> {
        return userService.deleteById(id)
    }
}


