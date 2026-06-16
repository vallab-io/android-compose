package vallab.practice.screen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import vallab.practice.model.Card
import vallab.practice.repository.PaymentCardsRepository

class NewCardViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {

    private val _cardNumber = MutableStateFlow("")
    val cardNumber: StateFlow<String> = _cardNumber.asStateFlow()

    private val _expiredDate = MutableStateFlow("")
    val expiredDate: StateFlow<String> = _expiredDate.asStateFlow()

    private val _ownerName = MutableStateFlow("")
    val ownerName: StateFlow<String> = _ownerName.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _cardAdded = MutableStateFlow<Boolean>(false)
    val cardAdded: StateFlow<Boolean> = _cardAdded.asStateFlow()

    fun setCardNumber(cardNumber: String) {
        _cardNumber.value = cardNumber.filter { it.isDigit() }.take(16)
    }

    fun setExpiredDate(expiredDate: String) {
        _expiredDate.value = expiredDate.filter { it.isDigit() }.take(4)
    }

    fun setOwnerName(ownerName: String) {
        _ownerName.value = ownerName
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun addCard() {
        repository.addCard(
            Card(
                cardNumber = _cardNumber.value,
                expiredDate = _expiredDate.value,
                ownerName = _ownerName.value,
                password = _password.value,
            )
        )
        _cardAdded.value = true
    }
}