package vallab.practice.validation

class SignUpValidation {

    fun validateUserName(userName: String): UserNameValidation? = when {
        userName.isEmpty() -> null
        userName.length !in 2..5 -> UserNameValidation.INVALID_LENGTH
        !userName.matches(Regex(USERNAME_REGEX)) -> UserNameValidation.INVALID_FORMAT
        else -> null
    }

    fun validateEmail(email: String): EmailValidation? = when {
        email.isEmpty() -> null
        !email.matches(Regex(EMAIL_REGEX)) -> EmailValidation.INVALID_FORMAT
        else -> null
    }

    fun validatePassword(password: String): PasswordValidation? = when {
        password.isEmpty() -> null
        password.length !in 8..16 -> PasswordValidation.INVALID_LENGTH
        !password.matches(Regex(PASSWORD_REGEX)) -> PasswordValidation.INVALID_FORMAT
        else -> null
    }

    fun validatePasswordConfirm(password: String, passwordConfirm: String): PasswordConfirmValidation? = when {
        passwordConfirm.isEmpty() -> null
        password != passwordConfirm -> PasswordConfirmValidation.MISMATCH
        else -> null
    }

    companion object {
        private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
        private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
        private const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"
    }

}