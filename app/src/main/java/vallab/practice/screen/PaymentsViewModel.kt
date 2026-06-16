package vallab.practice.screen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import vallab.practice.repository.PaymentCardsRepository

class PaymentsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<CreditCardUiState>(CreditCardUiState.Empty)
    val uiState: StateFlow<CreditCardUiState> = _uiState.asStateFlow()

    init {
        fetchCards()
    }

    fun fetchCards() {
        val cards = PaymentCardsRepository.cards
        _uiState.value = when (cards.size) {
            0 -> CreditCardUiState.Empty
            1 -> CreditCardUiState.One(cards[0])
            else -> CreditCardUiState.Many(cards)
        }
    }

}