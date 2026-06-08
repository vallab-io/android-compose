package vallab.practice

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import vallab.practice.screen.SignUpScreen
import vallab.practice.ui.theme.PracticeTheme

class SignUpScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Before
    fun setUp() {
        composeTestRule.setContent {
            PracticeTheme {
                SignUpScreen()
            }
        }
    }


    @Test
    fun `모든_값이_유효하면_가입_버튼이_활성화된다`() {

        composeTestRule.onNodeWithText("UserName").performTextInput("김김김")
        composeTestRule.onNodeWithText("email").performTextInput("android12@naver.com")
        composeTestRule.onNodeWithText("Password").performTextInput("a12345678")
        composeTestRule.onNodeWithText("Password Confirm").performTextInput("a12345678")

        composeTestRule.onNodeWithText("sign up").assertIsEnabled()
    }

    @Test
    fun `비밀번호가_일치하지_않으면_에러메시지가_보이고_가입_버튼이_비활성화된다`() {
        composeTestRule.onNodeWithText("UserName").performTextInput("김김김")
        composeTestRule.onNodeWithText("email").performTextInput("android12@naver.com")
        composeTestRule.onNodeWithText("Password").performTextInput("a12345678")
        composeTestRule.onNodeWithText("Password Confirm").performTextInput("a1234567999")
        composeTestRule.onNodeWithText("비밀번호가 일치하지 않습니다.").assertExists()


        composeTestRule.onNodeWithText("sign up").assertIsNotEnabled()
    }

    @Test
    fun `입력이_하나라도_부족하면_가입_버튼이_비활성화된다`() {

        // UserName 미입력
        composeTestRule.onNodeWithText("email").performTextInput("android12@naver.com")
        composeTestRule.onNodeWithText("Password").performTextInput("a12345678")
        composeTestRule.onNodeWithText("Password Confirm").performTextInput("a12345678")

        composeTestRule.onNodeWithText("sign up").assertIsNotEnabled()
    }

    @Test
    fun `입력이_하나라도_유효하지_않으면_가입_버튼이_비활성화되고_에러메시지_노출`() {

        composeTestRule.onNodeWithText("UserName").performTextInput("김") // UserName 짧음
        composeTestRule.onNodeWithText("email").performTextInput("android12@naver.com")
        composeTestRule.onNodeWithText("Password").performTextInput("a12345678")
        composeTestRule.onNodeWithText("Password Confirm").performTextInput("a12345678")

        composeTestRule
            .onNodeWithText("이름은 2자 이상 5자 이하로 입력해주세요.")
            .assertExists()

        composeTestRule.onNodeWithText("sign up").assertIsNotEnabled()
    }
}