package vallab.practice.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import vallab.practice.model.BankType
import vallab.practice.model.Card
import vallab.practice.repository.PaymentCardsRepository

class NewCardViewModel(
    private val savedStateHandle: SavedStateHandle = SavedStateHandle(),
    private val repository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {

    private var modifyCardIndex: Int? = savedStateHandle["card_index"]

    private lateinit var originalCard: Card

    private val _uiState = MutableStateFlow(NewCardUiState())
    val uiState: StateFlow<NewCardUiState> = _uiState.asStateFlow()

    val isChanged: StateFlow<Boolean> = uiState
        .map { state ->
            if (!state.isModifying) false
            else currentCard() != originalCard
            }

        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = false,
        )

    fun loadCard(index: Int) {
        val card = repository.cards.getOrNull(index) ?: return
        modifyCardIndex = index
        originalCard = card
        _uiState.value = NewCardUiState(
            cardNumber = card.cardNumber,
            expiredDate = card.expiredDate,
            ownerName = card.ownerName,
            password = card.password,
            bankType = card.bankType,
            isModifying = true
        )
    }

    fun setCardNumber(cardNumber: String) {
        _uiState.update {
            it.copy(cardNumber = cardNumber.filter { number -> number.isDigit() }.take(16))
        }
    }

    fun setExpiredDate(expiredDate: String) {
        _uiState.update {
            it.copy(expiredDate = expiredDate.filter { number -> number.isDigit() }.take(4))
        }
    }

    fun setOwnerName(ownerName: String) {
        _uiState.update { it.copy(ownerName = ownerName) }
    }

    fun setPassword(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun setBankType(bankType: BankType) {
        _uiState.update { it.copy(bankType = bankType) }
    }

    fun addCard() {
        val card = currentCard()
        modifyCardIndex?.let { repository.updateCard(it, card) } ?: repository.addCard(card)
        _uiState.update { it.copy(cardAdded = true) }
    }

    private fun currentCard() = with(_uiState.value) {
        Card(
            cardNumber = cardNumber,
            expiredDate = expiredDate,
            ownerName = ownerName,
            password = password,
            bankType = bankType,
        )
    }
}