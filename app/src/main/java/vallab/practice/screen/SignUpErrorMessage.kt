package vallab.practice.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import vallab.practice.R
import vallab.practice.validation.EmailError
import vallab.practice.validation.PasswordError
import vallab.practice.validation.PasswordMatchError
import vallab.practice.validation.UserNameError

@Composable
fun userNameErrorMessage(error: UserNameError): String? = when (error) {
    UserNameError.LENGTH -> stringResource(R.string.username_length_error)
    UserNameError.FORMAT -> stringResource(R.string.username_format_error)
}

@Composable
fun emailErrorMessage(error: EmailError): String? = when (error) {
    EmailError.FORMAT -> stringResource(R.string.email_format_error)
}

@Composable
fun passwordErrorMessage(error: PasswordError): String? = when (error) {
    PasswordError.LENGTH -> stringResource(R.string.password_length)
    PasswordError.FORMAT -> stringResource(R.string.password_format_error)
}

@Composable
fun passwordMatchErrorMessage(error: PasswordMatchError): String? = when (error) {
    PasswordMatchError.MISMATCH -> stringResource(R.string.password_match_error)
}


