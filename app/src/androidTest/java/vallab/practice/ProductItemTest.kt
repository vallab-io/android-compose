package vallab.practice

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import vallab.practice.component.ProductItem
import vallab.practice.model.Product

class ProductItemTest {

    @get:Rule

    val composeTestRule = createComposeRule()

    private val product = mutableStateOf(
        Product(
            id = 1,
            name = "테스트 상품",
            price = 12000,
            imageUrl = ""
        )
    )


    @Before
    fun setUp() {
        composeTestRule.setContent {
            ProductItem(product = product.value, onClick = {})
        }
    }


    @Test
    fun `큰_금액이_포맷형태로_표시된다`() {
        // when
        product.value = product.value.copy(price = 123456789)

        // then
        composeTestRule
            .onNodeWithText("123,456,789원")
            .assertExists()
    }

}
