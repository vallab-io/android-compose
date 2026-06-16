//package vallab.practice
//
//import androidx.compose.runtime.remember
//import androidx.compose.ui.test.junit4.createComposeRule
//import androidx.compose.ui.test.onNodeWithText
//import org.junit.Rule
//import org.junit.Test
//import vallab.practice.screen.NewCardScreen
//import vallab.practice.screen.NewCardViewModel
//import vallab.practice.ui.theme.PracticeTheme
//
//class NewCardScreenTest {
//
//    @get:Rule
//
//    val composeRule = createComposeRule()
//
//
//    @Test
//    fun `사용자_이름_길이_에러메시지_표시`() {
//        composeRule.setContent {
//            PracticeTheme {
//                val viewModel = remember {
//                    NewCardViewModel().apply {
//                        setOwnerName("김".repeat(31))
//                    }
//                }
//                NewCardScreen(viewModel = viewModel)
//            }
//        }
//
//        composeRule
//            .onNodeWithText("이름은 30자 이하로 입력해주세요.")
//            .assertExists()
//    }
//
//
//    @Test
//    fun `사용자_이름_길이_정상_입력`() {
//        composeRule.setContent {
//            PracticeTheme {
//                val viewModel = remember {
//                    NewCardViewModel().apply {
//                        setOwnerName("김".repeat(30))
//                    }
//                }
//                NewCardScreen(viewModel = viewModel)
//            }
//        }
//
//        composeRule
//            .onNodeWithText("이름은 30자 이하로 입력해주세요.")
//            .assertDoesNotExist()
//    }
//
//
//    @Test
//    fun `카드번호_입력시_구분자_표시`() {
//        composeRule.setContent {
//            PracticeTheme {
//                val viewModel = remember {
//                    NewCardViewModel().apply {
//                        setCardNumber("1234567812345678")
//                    }
//                }
//                NewCardScreen(viewModel = viewModel)
//            }
//        }
//        composeRule
//            .onNodeWithText("1234 - 5678 - 1234 - 5678")
//            .assertExists()
//    }
//
//    @Test
//    fun `만료일_입력시_슬래시_표시`() {
//        composeRule.setContent {
//            PracticeTheme {
//                val viewModel = remember {
//                    NewCardViewModel().apply {
//                        setExpiredDate("0123")
//                    }
//                }
//                NewCardScreen(viewModel = viewModel)
//            }
//        }
//        composeRule
//            .onNodeWithText("01 / 23")
//            .assertExists()
//    }
//
//}