package vallab.practice.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import vallab.practice.model.BankType
import vallab.practice.model.Card
import vallab.practice.repository.PaymentCardsRepository

class NewCardViewModel(
    private val savedStateHandle: SavedStateHandle = SavedStateHandle(),
    private val repository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {

    private var modifyCardIndex: Int? = savedStateHandle["card_index"]

    private lateinit var originalCard: Card
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

    private val _bankType = MutableStateFlow(BankType.NOT_SELECTED)
    val bankType: StateFlow<BankType> = _bankType.asStateFlow()

    private val _isModifying = MutableStateFlow(modifyCardIndex != null)
    val isModifying: StateFlow<Boolean> = _isModifying.asStateFlow()

    private val _isChanged = MutableStateFlow(false)
    val isChanged: StateFlow<Boolean> = _isChanged.asStateFlow()


    init {
        modifyCardIndex?.let { loadCard(it) }
    }

    fun loadCard(index: Int) {
        val card = repository.cards.getOrNull(index) ?: return
        modifyCardIndex = index
        originalCard = card
        _cardNumber.value = card.cardNumber
        _expiredDate.value = card.expiredDate
        _ownerName.value = card.ownerName
        _password.value = card.password
        _bankType.value = card.bankType
        _isModifying.value = true
        _isChanged.value = false
    }

    fun setCardNumber(cardNumber: String) {
        _cardNumber.value = cardNumber.filter { it.isDigit() }.take(16)
        checkIsChanged()
    }

    fun setExpiredDate(expiredDate: String) {
        _expiredDate.value = expiredDate.filter { it.isDigit() }.take(4)
        checkIsChanged()
    }

    fun setOwnerName(ownerName: String) {
        _ownerName.value = ownerName
        checkIsChanged()
    }

    fun setPassword(password: String) {
        _password.value = password
        checkIsChanged()
    }

    fun setBankType(bankType: BankType) {
        _bankType.value = bankType
        checkIsChanged()
    }

    fun addCard() {
        val card = currentCard()
        modifyCardIndex?.let { repository.updateCard(it, card) } ?: repository.addCard(card)
        _cardAdded.value = true

    }

    private fun currentCard() = Card(
        cardNumber = _cardNumber.value,
        expiredDate = _expiredDate.value,
        ownerName = _ownerName.value,
        password = _password.value,
        bankType = _bankType.value,
    )

    private fun checkIsChanged() {
        if (!_isModifying.value) return
        _isChanged.value = currentCard() != originalCard
    }
}