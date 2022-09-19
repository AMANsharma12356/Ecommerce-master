package com.example.Ecommerce.model

import org.springframework.data.annotation.Id

class Login(
    @Id
    val id: String,
    var userName: String,
    var password: String
)
