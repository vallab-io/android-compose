package vallab.practice.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vallab.practice.R
import vallab.practice.component.PasswordTextField
import vallab.practice.component.SignUpTextField
import vallab.practice.ui.theme.PracticeTheme
import vallab.practice.validation.SignUpValidation

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onShowMessage: (String) -> Unit = {},
) {
    val context = LocalContext.current

    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }

    val signUpValidation = remember { SignUpValidation() }

    val userNameError = signUpValidation.validateUserName(userName)
    val emailError = signUpValidation.validateEmail(email)
    val passwordError = signUpValidation.validatePassword(password)
    val passwordMatchError = signUpValidation.validatePasswordConfirm(password, passwordConfirm)

    val isButtonEnabled = userName.isNotBlank() && userNameError == null &&
            email.isNotBlank() && emailError == null &&
            password.isNotBlank() && passwordError == null &&
            passwordConfirm.isNotBlank() && passwordMatchError == null

    Column(
        modifier = modifier
            .padding(top = 112.dp, start = 32.dp, end = 32.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.signup_title),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth()
        )

        SignUpTextField(
            modifier = Modifier.padding(top = 36.dp),
            value = userName,
            label = stringResource(R.string.username_label),
            isError = userNameError != null,
            errorMessage = userNameError?.let { userNameErrorMessage(it) },
            onValueChange = { userName = it },
        )


        SignUpTextField(
            modifier = Modifier.padding(top = 30.dp),
            value = email,
            isError = emailError != null,
            errorMessage = emailError?.let { emailErrorMessage(it) },
            label = stringResource(R.string.email_label),
            onValueChange = { email = it },
        )


        PasswordTextField(
            modifier = Modifier.padding(top = 30.dp),
            value = password,
            label = stringResource(R.string.password_label),
            onValueChange = { password = it },
            isError = passwordError != null,
            errorMessage = passwordError?.let { passwordErrorMessage(it) }
        )

        PasswordTextField(
            modifier = Modifier.padding(top = 30.dp),
            value = passwordConfirm,
            onValueChange = { passwordConfirm = it },
            label = stringResource(R.string.password_confirm_label),
            isError = passwordMatchError != null,
            errorMessage = passwordMatchError?.let { passwordMatchErrorMessage(it) }

        )

        Button(
            onClick = {
                onShowMessage(context.getString(R.string.signup_success))
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.blue_100)
            ),
            enabled = isButtonEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
        ) {
            Text(text = stringResource(R.string.sign_up))
        }
    }
}


@Preview(name = "모든값이 있을때", showBackground = true)
@Composable
private fun SignUpScreen_Preview() {
    PracticeTheme {
        Column(
            modifier = Modifier
                .padding(top = 112.dp, start = 32.dp, end = 32.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to Compose 🚀",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.fillMaxWidth(),
            )
            SignUpTextField(
                modifier = Modifier.padding(top = 36.dp),
                value = "김김김",
                onValueChange = {},
                label = "UserName",
            )
            SignUpTextField(
                modifier = Modifier.padding(top = 30.dp),
                value = "android12@naver.com",
                onValueChange = {},
                label = "email",
            )
            PasswordTextField(
                modifier = Modifier.padding(top = 30.dp),
                value = "abc12345",
                onValueChange = {},
                label = "Password",
            )
            PasswordTextField(
                modifier = Modifier.padding(top = 30.dp),
                value = "abc12345",
                onValueChange = {},
                label = "Password Confirm",
            )
            Button(
                onClick = {},
                enabled = true,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.blue_100),
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
            ) {
                Text("sign up")
            }
        }
    }
}

@Preview(name = "모든 상태 에러", showBackground = true)
@Composable
private fun SignUpScreenPreview_AllError() {
    PracticeTheme {
        Column(
            modifier = Modifier
                .padding(top = 112.dp, start = 32.dp, end = 32.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Welcome to Compose 🚀",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.fillMaxWidth(),
            )
            SignUpTextField(
                modifier = Modifier.padding(top = 36.dp),
                value = "김",
                onValueChange = {},
                label = "UserName",
                isError = true,
                errorMessage = "이름은 2자 이상 5자 이하로 입력해주세요.",
            )
            SignUpTextField(
                modifier = Modifier.padding(top = 30.dp),
                value = "android@",
                onValueChange = {},
                label = "email",
                isError = true,
                errorMessage = "이메일 형식이 올바르지 않습니다.",
            )
            PasswordTextField(
                modifier = Modifier.padding(top = 30.dp),
                value = "abc123",
                onValueChange = {},
                label = "Password",
                isError = true,
                errorMessage = "비밀번호는 8~16자여야 합니다.",
            )
            PasswordTextField(
                modifier = Modifier.padding(top = 30.dp),
                value = "abc12346",
                onValueChange = {},
                label = "Password Confirm",
                isError = true,
                errorMessage = "비밀번호가 일치하지 않습니다.",
            )
            Button(
                onClick = {},
                enabled = false,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.blue_100),
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
            ) {
                Text("sign up")
            }
        }
    }
}

