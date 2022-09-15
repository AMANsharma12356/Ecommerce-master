
import arrow.core.mapOf
import arrow.product
import com.example.Ecommerce.controller.ProductController
import com.example.Ecommerce.model.Product
import com.example.Ecommerce.repository.ProductRepository
import com.example.Ecommerce.service.ProductService
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.returnResult
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@WebFluxTest(ProductController::class)
@AutoConfigureWebTestClient
class ProductControlllerTest {

    @Autowired
    lateinit var client: WebTestClient

    @Autowired
    lateinit var producTestCotService: ProductService

    @TestConfiguration
    class ControllerTestConfig {
        @Bean
        fun productService() = mockk<ProductService>()
    }


    @Test
    fun `register product and update user when api is called`() {

        val exepectedResponse = mapOf(
            "productId" to "111",
            "productName" to "Nokia",
            "productPrice" to "15000"
        )
        val product = Product(
            "111", "Nokia", "15000"
        )
        every {
            productService.addProduct(product)
        } returns Mono.just(product)

        val response = client.post()
            .uri("/products/add")
            .bodyValue(product)
            .exchange()
            .expectStatus().is2xxSuccessful
            .returnResult<Any>().responseBody

        response.blockFirst() shouldBe exepectedResponse

        verify(exactly = 1) {
            productService.addProduct(product)
        }
    }
}

