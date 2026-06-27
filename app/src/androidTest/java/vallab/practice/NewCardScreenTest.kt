package vallab.practice

import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import vallab.practice.model.BankType
import vallab.practice.model.Card
import vallab.practice.repository.PaymentCardsRepository
import vallab.practice.screen.NewCardScreen
import vallab.practice.screen.NewCardViewModel
import vallab.practice.ui.theme.PracticeTheme

class NewCardScreenTest {

    @get:Rule

    val composeRule = createComposeRule()

    private val testCard = Card(
        cardNumber = "1234567812345678",
        expiredDate = "1234",
        ownerName = "홍길동",
        password = "1234",
        bankType = BankType.BC,
    )


    @Test
    fun `사용자_이름_길이_에러메시지_표시`() {
        composeRule.setContent {
            PracticeTheme {
                val viewModel = remember {
                    NewCardViewModel().apply {
                        setOwnerName("김".repeat(31))
                    }
                }
                NewCardScreen(
                    viewModel = viewModel,
                    onBackClick = {},
                    navigateToCardList = {})
            }
        }

        composeRule
            .onNodeWithText("이름은 30자 이하로 입력해주세요.")
            .assertExists()
    }


    @Test
    fun `사용자_이름_길이_정상_입력`() {
        composeRule.setContent {
            PracticeTheme {
                val viewModel = remember {
                    NewCardViewModel().apply {
                        setOwnerName("김".repeat(30))
                    }
                }
                NewCardScreen(
                    viewModel = viewModel,
                    onBackClick = {},
                    navigateToCardList = {})
            }
        }

        composeRule
            .onNodeWithText("이름은 30자 이하로 입력해주세요.")
            .assertDoesNotExist()
    }


    @Test
    fun `카드번호_입력시_구분자_표시`() {
        composeRule.setContent {
            PracticeTheme {
                val viewModel = remember {
                    NewCardViewModel().apply {
                        setCardNumber("1234567812345678")
                    }
                }
                NewCardScreen(
                    viewModel = viewModel,
                    onBackClick = {},
                    navigateToCardList = {})
            }
        }
        composeRule
            .onNodeWithText("1234 - 5678 - 1234 - 5678")
            .assertExists()
    }

    @Test
    fun `만료일_입력시_슬래시_표시`() {
        composeRule.setContent {
            PracticeTheme {
                val viewModel = remember {
                    NewCardViewModel().apply {
                        setExpiredDate("0123")
                    }
                }
                NewCardScreen(
                    viewModel = viewModel,
                    onBackClick = {},
                    navigateToCardList = {})
            }
        }
        composeRule
            .onNodeWithText("01 / 23")
            .assertExists()
    }


    @Test
    fun `수정_시에는_TopBar_제목이_카드_수정_이어야한다`() {
        composeRule.setContent {
            PracticeTheme {
                val viewModel = remember {
                    NewCardViewModel().apply {
                        PaymentCardsRepository.addCard(testCard)
                        loadCard(0)
                    }
                }
                NewCardScreen(
                    viewModel = viewModel,
                    onBackClick = {},
                    navigateToCardList = {})
            }

        }
        composeRule.onNodeWithText("카드 수정").assertExists()
        composeRule.onNodeWithText("카드 추가").assertDoesNotExist()
    }


    @Test
    fun `수정하러_진입했을_때_기존_정보가_존재해야_한다`() {

        composeRule.setContent {
            PracticeTheme {
                val viewModel = remember {
                    NewCardViewModel().apply {
                        PaymentCardsRepository.addCard(testCard)
                        loadCard(0)
                    }
                }
                NewCardScreen(
                    viewModel = viewModel,
                    onBackClick = {},
                    navigateToCardList = {})
            }
        }
        composeRule.onNodeWithText("홍길동").assertExists()
    }


    @Test
    fun `수정이_일어나지_않았을_때_버튼이_비활성화_되어야_한다`() {
        composeRule.setContent {
            PracticeTheme {
                val viewModel = remember {
                    NewCardViewModel().apply {
                        PaymentCardsRepository.addCard(testCard)
                        loadCard(0)
                    }
                }
                NewCardScreen(
                    viewModel = viewModel,
                    onBackClick = {},
                    navigateToCardList = {})
            }
        }
        composeRule
            .onNodeWithContentDescription("완료")
            .assertIsNotEnabled()
    }


    @Test
    fun `수정이_일어났을_때_버튼이_활성화_되어야_한다`() {
        composeRule.setContent {
            PracticeTheme {
                val viewModel = remember {
                    NewCardViewModel().apply {
                        PaymentCardsRepository.addCard(testCard)
                        loadCard(0)
                        setOwnerName("김김김")
                    }
                }
                NewCardScreen(
                    viewModel = viewModel,
                    onBackClick = {},
                    navigateToCardList = {})
            }
        }
        composeRule
            .onNodeWithContentDescription("완료")
            .assertIsEnabled()
    }
}


