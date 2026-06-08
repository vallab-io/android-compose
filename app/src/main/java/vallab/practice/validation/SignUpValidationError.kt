package vallab.practice.validation

enum class UserNameValidation{
    INVALID_LENGTH,
    INVALID_FORMAT
}

enum class EmailValidation{
    INVALID_FORMAT
}

enum class PasswordValidation{
    INVALID_LENGTH,
    INVALID_FORMAT
}

enum class PasswordConfirmValidation{
    MISMATCH
}