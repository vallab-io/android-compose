package vallab.practice.validation

enum class UserNameError{
    LENGTH,
    FORMAT
}

enum class EmailError{
    FORMAT
}

enum class PasswordError{
    LENGTH,
    FORMAT
}

enum class PasswordMatchError{
    MISMATCH
}