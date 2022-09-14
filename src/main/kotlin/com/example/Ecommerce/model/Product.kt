package com.example.Ecommerce.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Product(
    @Id
    var productId: String?,
    var productName: String,
    var productPrice: String,
)