package com.example.Ecommerce.repository

import com.example.Ecommerce.model.Login
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface LoginRepository : ReactiveMongoRepository<Login, String> {



}
