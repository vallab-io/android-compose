package vallab.practice

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import vallab.practice.component.ProductItem
import vallab.practice.model.Cart
import vallab.practice.model.CartItem
import vallab.practice.model.dummyProducts
import vallab.practice.ui.theme.PracticeTheme

class ProductItemTest {

    @get:Rule

    val composeTestRule = createComposeRule()

    private val product = mutableStateOf(dummyProducts[0])


    @Test
    fun `큰_금액이_포맷형태로_표시된다`() {
        // when
        product.value = product.value.copy(price = 123456789)

        // then
        composeTestRule
            .onNodeWithText("123,456,789원")
            .assertExists()
    }

    @Test
    fun `기본적으로_상품위에_FAB가_보인다`() {
        composeTestRule
            .onNodeWithContentDescription("수량 조절 버튼")
            .assertExists()
    }

    @Test
    fun `상품이_1개라도_담기면_수량표시가_나온다`() {
        composeTestRule.setContent {
            ProductItem(
                product = product.value,
                cartItem = CartItem(product.value, count = 1),
                onClick = {},
            )
        }
        composeTestRule.onNodeWithContentDescription("수량 플러스").assertExists()
        composeTestRule.onNodeWithContentDescription("수량 마이너스").assertExists()
    }


    @Test
    fun `수량을_추가하면_Cart에_반영된다`() {
        composeTestRule.setContent {
            PracticeTheme {
                ProductItem(
                    product = product.value,
                    onClick = {},
                    onCountPlus = { Cart.addOne(product.value) },
                )
            }
        }
        composeTestRule
            .onNodeWithContentDescription("수량 조절 버튼")
            .performClick()

        assertTrue(Cart.items.any { it.product.id == product.value.id })
    }
}
