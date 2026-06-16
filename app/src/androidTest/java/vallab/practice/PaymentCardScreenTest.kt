package vallab.practice

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import vallab.practice.model.Card
import vallab.practice.screen.CreditCardUiState
import vallab.practice.screen.PaymentsScreenContent
import vallab.practice.ui.theme.PracticeTheme

class PaymentCardScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()


    private val testCard = Card(
        cardNumber = "1234567812345678",
        expiredDate = "1234",
        ownerName = "홍길동",
        password = "1234",
    )


    @Test
    fun `Empty_상태일_때_앱바의_문구가_노출되어야_한다`() {
        composeTestRule.setContent {
            PracticeTheme {
                PaymentsScreenContent(
                    uiState = CreditCardUiState.Empty,
                    onAddCardClick = {},
                )
            }
        }

        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertExists()
    }

    @Test
    fun `One_상태일_때_카드의_정보가_노출되어야_한다`() {
        composeTestRule.setContent {
            PracticeTheme {
                PaymentsScreenContent(
                    uiState = CreditCardUiState.One(testCard),
                    onAddCardClick = {},
                )
            }
        }
        composeTestRule
            .onNodeWithText("홍길동")
            .assertExists()
        composeTestRule
            .onNodeWithText("1234 - 5678 - **** - ****")
            .assertExists()
        composeTestRule
            .onNodeWithText("추가")
            .assertDoesNotExist()
    }

    @Test
    fun `Many_상태일_때_카드의_정보와_추가_버튼이_노출되어야_한다`() {
        composeTestRule.setContent {
            PracticeTheme {
                PaymentsScreenContent(
                    uiState = CreditCardUiState.Many(
                        listOf(
                            testCard,
                            testCard.copy(ownerName = "김철수"),
                        )
                    ),
                    onAddCardClick = {},
                )
            }
        }
        composeTestRule
            .onNodeWithText("홍길동")
            .assertExists()
        composeTestRule
            .onNodeWithText("김철수")
            .assertExists()
        composeTestRule
            .onNodeWithText("추가")
            .assertExists()
    }
}