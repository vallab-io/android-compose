package vallab.practice

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import vallab.practice.model.Cart
import vallab.practice.model.Cart.removeAll
import vallab.practice.model.dummyProducts
import vallab.practice.screen.CartDetailScreen
import vallab.practice.ui.theme.PracticeTheme

class CartDetailScreenTest {


    @get:Rule
    val composeTestRule = createComposeRule()

    private val product1 = dummyProducts[0]
    private val product2 = dummyProducts[1]

    private val testProducts = listOf(product1, product2)

    @Before
    fun setup() {
        testProducts.forEach { removeAll(it) }
    }


    @Test
    fun `담긴_상품_가격의_총합이_노출된다`() {
        // given
        Cart.addOne(product1)
        Cart.addOne(product2)
        composeTestRule.setContent {
            PracticeTheme {
                CartDetailScreen(onBackButtonClick = {})
            }
        }

        // then
        composeTestRule.onNodeWithText("주문하기(22,000원)")
            .assertExists()

    }


    @Test
    fun `담긴_상품을_제거할_수_있다`() {
        // given
        Cart.addOne(product1)
        composeTestRule.setContent {
            PracticeTheme {
                CartDetailScreen(onBackButtonClick = {})
            }
        }

        // when
        composeTestRule
            .onNodeWithContentDescription("상품 제거")
            .performClick()

        // then
        composeTestRule.onNodeWithText("주문하기(0원)")
            .assertExists()
    }


    @Test
    fun `담긴_상품의_수량을_증가시키면_상품_가격에_반영된다`() {
        // given
        Cart.addOne(product1)
        composeTestRule.setContent {
            PracticeTheme {
                CartDetailScreen(onBackButtonClick = {})
            }
        }

        // when
        composeTestRule
            .onNodeWithContentDescription("수량 플러스")
            .performClick()

        // then
        composeTestRule
            .onNodeWithText("2")
            .assertExists()

        composeTestRule
            .onNodeWithText("주문하기(20,000원)")
            .assertExists()
    }


    @Test
    fun `담긴_상품의_수량을_감소시키면_상품_가격에_반영된다`() {
        // given
        Cart.addOne(product1)
        Cart.addOne(product1)
        Cart.addOne(product1)
        composeTestRule.setContent {
            PracticeTheme {
                CartDetailScreen(onBackButtonClick = {})
            }
        }

        // when
        composeTestRule
            .onNodeWithContentDescription("수량 마이너스")
            .performClick()

        // then
        composeTestRule
            .onNodeWithText("2")
            .assertExists()

        composeTestRule
            .onNodeWithText("주문하기(20,000원)")
            .assertExists()
    }


    @Test
    fun `담긴_상품의_수량을_1보다_적게_하면_상품이_삭제된다`() {
        // given
        Cart.addOne(product1)
        composeTestRule.setContent {
            PracticeTheme {
                CartDetailScreen(onBackButtonClick = {})
            }
        }

        // when
        composeTestRule
            .onNodeWithContentDescription("수량 마이너스")
            .performClick()

        // then
        composeTestRule
            .onNodeWithText(product1.name)
            .assertDoesNotExist()
    }
}