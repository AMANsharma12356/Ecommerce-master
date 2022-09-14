package com.example.Ecommerce.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(
    @Id
    var userId: String?,
    var userName: String,
    var userContactno: String,
    var userPassword: String,
)