package vallab.practice.validation

class SignUpValidation {

    fun userNameError(userName: String): UserNameError? = when {
        userName.isEmpty() -> null
        userName.length !in 2..5 -> UserNameError.LENGTH
        !userName.matches(Regex(USERNAME_REGEX)) -> UserNameError.FORMAT
        else -> null
    }

    fun emailError(email: String): EmailError? = when {
        email.isEmpty() -> null
        !email.matches(Regex(EMAIL_REGEX)) -> EmailError.FORMAT
        else -> null
    }

    fun passwordError(password: String): PasswordError? = when {
        password.isEmpty() -> null
        password.length !in 8..16 -> PasswordError.LENGTH
        !password.matches(Regex(PASSWORD_REGEX)) -> PasswordError.FORMAT
        else -> null
    }

    fun passwordMatchError(password: String, passwordConfirm: String): PasswordMatchError? = when {
        passwordConfirm.isEmpty() -> null
        password != passwordConfirm -> PasswordMatchError.MISMATCH
        else -> null
    }

    companion object {
        private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
        private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
        private const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"
    }

}