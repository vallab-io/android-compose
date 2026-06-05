package vallab.practice

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import vallab.practice.model.Cart
import vallab.practice.model.dummyProducts
import vallab.practice.screen.CartScreen
import vallab.practice.ui.theme.PracticeTheme

class CartScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val product = dummyProducts[0]


    @Before
    fun setup() {
        Cart.removeAll(product)
        composeTestRule.setContent {
            PracticeTheme { CartScreen() }
        }
    }

    @Test
    fun `플러스_버튼을_누르면_수량_조절_표시가_나온다`() {

        composeTestRule
            .onAllNodesWithContentDescription("수량 조절 버튼")
            .onFirst()
            .performClick()

        composeTestRule.onNodeWithText("1").assertExists()
        composeTestRule.onNodeWithContentDescription("수량 플러스").assertExists()
    }


    @Test
    fun `목록에서_수량을_증가시킬_수_있다`() {

        composeTestRule
            .onAllNodesWithContentDescription("수량 조절 버튼")
            .onFirst()
            .performClick()

        composeTestRule
            .onNodeWithContentDescription("수량 플러스")
            .performClick()

        composeTestRule.onNodeWithText("2")
            .assertExists()

        val cartItem = Cart.items.find { it.product.id == product.id }

        assertEquals(2, cartItem?.count)
    }


    @Test
    fun `수량_조절_화면에서_마이너스를_누르면_FAB로_돌아간다`() {
        composeTestRule
            .onAllNodesWithContentDescription("수량 조절 버튼")
            .onFirst()
            .performClick()
        composeTestRule
            .onNodeWithContentDescription("수량 마이너스")
            .performClick()

        composeTestRule
            .onNodeWithContentDescription("수량 조절 버튼")
            .assertExists()
    }

}