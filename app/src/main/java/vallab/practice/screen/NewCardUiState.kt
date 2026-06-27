package vallab.practice.screen

import vallab.practice.model.BankType
import vallab.practice.model.Card

data class NewCardUiState(
    val cardNumber: String = "",
    val expiredDate: String = "",
    val ownerName: String = "",
    val password: String = "",
    val bankType: BankType = BankType.NOT_SELECTED,
    val isModifying: Boolean = false,
    val cardAdded: Boolean = false,
)