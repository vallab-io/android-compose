package vallab.practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import vallab.practice.model.dummyProducts
import vallab.practice.screen.ProductDetailScreen

class ProductDetailActivity : ComponentActivity() {

    private val productId: String by lazy {
        intent.getStringExtra("productId")
            ?: throw IllegalArgumentException("productId is required")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val id = productId.toIntOrNull() ?: return finish()
        val product = dummyProducts.find { it.id == id } ?: return finish()

        setContent {
            ProductDetailScreen(product = product, onBackButtonClick = { })
        }
    }

}
