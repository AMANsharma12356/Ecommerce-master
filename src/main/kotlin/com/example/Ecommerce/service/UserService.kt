package com.example.Ecommerce.service

import com.example.Ecommerce.model.User
import com.example.Ecommerce.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Service
class UserService(
    @Autowired
    val userRepository: UserRepository
)

{
    fun findAllusers() : Flux<User> {
        return userRepository.findAll()
    }

    fun addUser(user: User): Mono<User> {
        return userRepository.save(user)

    }
    fun updateUser(user: User):Mono<User>{
        return userRepository.save(user)
    }

    fun deleteById(id: String): Mono<Void>{
        return userRepository.deleteById(id)
    }

//    fun deleteAll(id: String): Mono<Void>{
//        return userRepository.deleteAll()
//    }

    fun findById(id: String): Mono<User>{
        return userRepository.findById(id)
    }

//    fun findAllUsers(): Any {
//        TODO("Not yet implemented")
//    }


//    companion object {
//        fun addUser(user: Any): Mono<User> {
//            TODO("Not yet implemented")
        }

