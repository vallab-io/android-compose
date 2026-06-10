package vallab.practice.validation


enum class OwnerNameValidation {
    INVALID_LENGTH
}

class CardValidation {
    fun validateOwnerName(name: String): OwnerNameValidation? = when {
        name.isEmpty() -> null
        name.length > 30 -> OwnerNameValidation.INVALID_LENGTH
        else -> null
    }
}