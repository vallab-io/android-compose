package vallab.practice

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import vallab.practice.component.PasswordTextField
import vallab.practice.component.SignUpTextField
import vallab.practice.validation.SignUpValidation

class InputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val username = mutableStateOf("")
    private val email = mutableStateOf("")
    private val password = mutableStateOf("")
    private val passwordConfirm = mutableStateOf("")

    @Before
    fun setUp() {
        val signUpValidation = SignUpValidation()

        composeTestRule.setContent {
            val userNameError = signUpValidation.userNameError(username.value)
            val emailError = signUpValidation.emailError(email.value)
            val passwordError = signUpValidation.passwordError(password.value)
            val passwordMatchError =
                signUpValidation.passwordMatchError(password.value, passwordConfirm.value)

            SignUpTextField(
                value = username.value,
                onValueChange = { username.value = it },
                label = "UserName",
                isError = userNameError != null,
                errorMessage = userNameError
            )

            SignUpTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = "email",
                isError = emailError != null,
                errorMessage = "이메일 형식이 올바르지 않습니다."
            )
            PasswordTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = "Password",
                isError = passwordError != null,
                errorMessage = passwordError
            )
            PasswordTextField(
                value = passwordConfirm.value,
                onValueChange = { passwordConfirm.value = it },
                label = "Password Confirm",
                isError = passwordMatchError != null,
                errorMessage = "비밀번호가 일치하지 않습니다."
            )
        }

    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // when
        username.value = "김컴포즈"

        // then
        composeTestRule
            .onNodeWithText("이름은 2~5자여야 합니다.")
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        // when
        username.value = "김컴포즈입니다"

        // then
        composeTestRule
            .onNodeWithText("이름은 2~5자여야 합니다.")
            .assertExists()
    }

    @Test
    fun 이메일_형식이_올바르지_않으면_에러메시지가_노출된다() {
        // when
        email.value = "android@"

        // then
        composeTestRule
            .onNodeWithText("이메일 형식이 올바르지 않습니다.")
            .assertExists()
    }

    @Test
    fun 비밀번호_길이가_다르면_에러메시지가_노출된다() {
        // when
        password.value = testPwShort()

        // then
        composeTestRule
            .onNodeWithText("비밀번호는 8~16자여야 합니다.")
            .assertExists()
    }

    @Test
    fun 비밀번호가_영문숫자조합이_아니면_에러메시지가_노출된다() {
        // when
        password.value = testPwInvalid()

        // then
        composeTestRule
            .onNodeWithText("비밀번호는 영문과 숫자를 포함해야 합니다.")
            .assertExists()
    }

    @Test
    fun 비밀번호가_일치하지_않으면_에러메시지가_노출된다() {
        // when
        password.value = testPwValid()
        passwordConfirm.value = testPwMismatch()

        // then
        composeTestRule
            .onNodeWithText("비밀번호가 일치하지 않습니다.")
            .assertExists()
    }

    @Test
    fun 모든_값이_올바르게_작성되면_에러메시지가_노출되지_않는다() {
        // when
        username.value = "홍길동"
        email.value = "android12@naver.com"
        password.value = testPwValid()
        passwordConfirm.value = testPwValid()

        // then
        composeTestRule.onNodeWithText("이름은 2~5자여야 합니다.").assertDoesNotExist()
        composeTestRule.onNodeWithText("이메일 형식이 올바르지 않습니다.").assertDoesNotExist()
        composeTestRule.onNodeWithText("비밀번호는 8~16자여야 합니다").assertDoesNotExist()
        composeTestRule.onNodeWithText("비밀번호는 영문과 숫자를 포함해야 합니다.").assertDoesNotExist()
        composeTestRule.onNodeWithText("비밀번호가 일치하지 않습니다").assertDoesNotExist()
    }

    private fun testPwShort() = "ab" + "123"
    private fun testPwInvalid() = "123" + "456789"
    private fun testPwValid() = "abc" + "12345"
    private fun testPwMismatch() = "abc" + "12346"
}