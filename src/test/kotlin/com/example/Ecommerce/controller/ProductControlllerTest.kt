
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

@WebFluxTest(ProductController::class)
@AutoConfigureWebTestClient
class ProductControlllerTest {

    @Autowired
    lateinit var client: WebTestClient

    @Autowired
    lateinit var productService: ProductService

    @Autowired
    lateinit var productRepository: ProductRepository

    @TestConfiguration
    class ControllerTestConfig {
        @Bean
        fun productService() = mockk<ProductService>()

        @Bean
        fun productRepository() = mockk<ProductRepository>()

    }

    @Test

    fun `should return list  `() {
        val product = Product(
            "12", "aman", "123"
        )

        val expectedResult = mapOf(
            "productId" to "12",
            "productName" to "aman",
            "productPrice" to "123"
        )
        every {
            productService.findAllproducts()
        } returns Flux.just(product)

        val response = client.get()
            .uri("/products/list")
            .accept(MediaType.APPLICATION_JSON)
            .exchange() //invoking the end point
            .expectStatus().is2xxSuccessful
            .returnResult<Any>()
            .responseBody

        response.blockFirst() shouldBe expectedResult

        verify(exactly = 1) {
            productService.findAllproducts()
        }
    }
}
