package vallab.practice.repository

import vallab.practice.model.Card

object PaymentCardsRepository {

    private val _cards = mutableListOf<Card>()
    val cards: List<Card> get() = _cards.toList()

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun updateCard(index: Int, card: Card) {
        _cards[index] = card
    }
}