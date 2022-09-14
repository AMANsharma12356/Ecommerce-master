package com.example.Ecommerce.service

import com.example.Ecommerce.model.User
import com.example.Ecommerce.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

//@Service
//class UserService(
//    @Autowired
//    val userRepository: UserRepository
//) {
//
//    fun addUser(user: User): Mono<User> {
//        if (user.userId == 0) {
//            throw UserIdException("User should id not zero")
//        }
//        return userRepository.save(user)
//    }
//
//    fun findAllUsers(): Flux<User> {
//        if (userRepository.findAll() == null) {
//            throw UserNotFoundException("User not present in Database")
//        }
//        return userRepository.findAll()
//    }
//
//    fun deleteUserById(userId: Int): Mono<Void> {
//        if (userId == 0) {
//            throw UserIdException("User should id should  not zero")
//        }
//        return userRepository.deleteById(userId)
//    }
//
//    fun updateUser(userId: Int, user: User): Mono<User> {
//        return userRepository.findById(userId)
//            .flatMap {
//                it.userId = user.userId
//                it.userName = user.userName
//                it.userContactno = user.userContactno
//                it.userPassword = user.userPassword
//                userRepository.save(it)
//            }
//    }
//
//    fun findById(id: String): Mono<User> {
//        return userRepository.findById(id)
//    }
//}
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



    companion object {
        fun addUser(user: Any): Mono<User> {
            TODO("Not yet implemented")
        }
    }

}