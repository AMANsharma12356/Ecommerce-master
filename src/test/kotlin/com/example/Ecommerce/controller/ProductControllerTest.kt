package com.example.Ecommerce.controller

import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest

@WebFluxTest(ProductController::class)
@AutoConfigureWebTestClient
class ProductControllerTest {

}