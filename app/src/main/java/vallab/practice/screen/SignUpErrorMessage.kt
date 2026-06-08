package vallab.practice.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import vallab.practice.R
import vallab.practice.validation.EmailValidation
import vallab.practice.validation.PasswordValidation
import vallab.practice.validation.PasswordConfirmValidation
import vallab.practice.validation.UserNameValidation

@Composable
fun userNameErrorMessage(error: UserNameValidation): String? = when (error) {
    UserNameValidation.INVALID_LENGTH -> stringResource(R.string.username_length_error)
    UserNameValidation.INVALID_FORMAT -> stringResource(R.string.username_format_error)
}

@Composable
fun emailErrorMessage(error: EmailValidation): String? = when (error) {
    EmailValidation.INVALID_FORMAT -> stringResource(R.string.email_format_error)
}

@Composable
fun passwordErrorMessage(error: PasswordValidation): String? = when (error) {
    PasswordValidation.INVALID_LENGTH -> stringResource(R.string.password_length)
    PasswordValidation.INVALID_FORMAT -> stringResource(R.string.password_format_error)
}

@Composable
fun passwordMatchErrorMessage(error: PasswordConfirmValidation): String? = when (error) {
    PasswordConfirmValidation.MISMATCH -> stringResource(R.string.password_match_error)
}


