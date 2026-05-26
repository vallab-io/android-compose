package vallab.practice

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import vallab.practice.component.SignUpTextField

class InputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val username = mutableStateOf("")

    @Before
    fun setUp() {
        composeTestRule.setContent {
            val userNameError = when {
                username.value.isEmpty() -> null
                username.value.length !in 2..5 -> USERNAME_LENGTH_ERROR
                else -> null
            }
            SignUpTextField(
                value = username.value,
                onValueChange = { username.value = it },
                label = "UserName",
                isError = userNameError != null,
                errorMessage = userNameError
            )
        }

    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // when
        username.value = "김컴포즈"

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        // when
        username.value = "김컴포즈입니다"

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertExists()
    }


    companion object {
        private const val USERNAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
    }
}