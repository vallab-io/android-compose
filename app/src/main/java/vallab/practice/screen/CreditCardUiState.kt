package vallab.practice.screen

import vallab.practice.model.Card


sealed interface CreditCardUiState {
    data object Empty : CreditCardUiState
    data class One(val card: Card) : CreditCardUiState
    data class Many(val cards: List<Card>) : CreditCardUiState
}