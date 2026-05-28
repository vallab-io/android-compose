package vallab.practice.validation

class SignUpValidation {

    fun userNameError(userName: String): String? = when {
        userName.isEmpty() -> null
        userName.length !in 2..5 -> USERNAME_LENGTH_ERROR
        !userName.matches(Regex(USERNAME_REGEX)) -> USERNAME_FORMAT_ERROR
        else -> null
    }

    fun emailError(email: String): String? = when {
        email.isEmpty() -> null
        !email.matches(Regex(EMAIL_REGEX)) -> EMAIL_FORMAT_ERROR
        else -> null
    }

    fun passwordError(password: String): String? = when {
        password.isEmpty() -> null
        password.length !in 8..16 -> PASSWORD_LENGTH_ERROR
        !password.matches(Regex(PASSWORD_REGEX)) -> PASSWORD_FORMAT_ERROR
        else -> null
    }

    fun passwordMatchError(password: String, passwordConfirm: String): String? = when {
        passwordConfirm.isEmpty() -> null
        password != passwordConfirm -> PASSWORD_MATCH_ERROR
        else -> null
    }

    companion object {
        const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
        const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
        const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"

        const val USERNAME_LENGTH_ERROR = "이름은 2자 이상 5자 이하로 입력해주세요."
        const val USERNAME_FORMAT_ERROR = "이름에는 숫자나 기호가 포함될 수 없습니다."
        const val EMAIL_FORMAT_ERROR = "이메일 형식이 올바르지 않습니다."
        const val PASSWORD_LENGTH_ERROR = "비밀번호는 8~16자여야 합니다"
        const val PASSWORD_FORMAT_ERROR = "비밀번호는 영문과 숫자를 포함해야 합니다."
        const val PASSWORD_MATCH_ERROR = "비밀번호가 일치하지 않습니다"
    }

}