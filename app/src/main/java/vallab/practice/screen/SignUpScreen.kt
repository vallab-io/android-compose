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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vallab.practice.R
import vallab.practice.component.PasswordTextField
import vallab.practice.component.SignUpTextField
import vallab.practice.ui.theme.PracticeTheme


const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"

@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }

    val userNameError = when {
        userName.isEmpty() -> null
        userName.length !in 2..5 -> stringResource(R.string.username_length)
        !userName.matches(Regex(USERNAME_REGEX)) -> stringResource(R.string.username_format)
        else -> null
    }

    val emailError = email.isNotEmpty() && !email.matches(Regex(EMAIL_REGEX))

    val passwordError = when {
        password.isEmpty() -> null
        password.length !in 8..16 -> stringResource(R.string.password_length)
        !password.matches(Regex(PASSWORD_REGEX)) -> stringResource(R.string.password_format)
        else -> null
    }

    val passwordMatchError = passwordConfirm.isNotEmpty() && password != passwordConfirm

    Column(
        modifier = Modifier
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
            errorMessage = userNameError,
            onValueChange = { userName = it },
        )


        SignUpTextField(
            modifier = Modifier.padding(top = 30.dp),
            value = email,
            isError = emailError,
            errorMessage = stringResource(R.string.email_error),
            label = stringResource(R.string.email_label),
            onValueChange = { email = it },
        )


        PasswordTextField(
            modifier = Modifier.padding(top = 30.dp),
            value = password,
            label = stringResource(R.string.password_label),
            onValueChange = { password = it },
            isError = passwordError != null,
            errorMessage = passwordError
        )

        PasswordTextField(
            modifier = Modifier.padding(top = 30.dp),
            value = passwordConfirm,
            onValueChange = { passwordConfirm = it },
            label = stringResource(R.string.password_confirm_label),
            isError = passwordMatchError,
            errorMessage = stringResource(R.string.password_not_match)

        )

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
        ) {
            Text(text = stringResource(R.string.sign_up))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    PracticeTheme {
        SignUpScreen()
    }
}