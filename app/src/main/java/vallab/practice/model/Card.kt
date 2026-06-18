package vallab.practice.model

data class Card(
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
    val bankType: BankType
)
