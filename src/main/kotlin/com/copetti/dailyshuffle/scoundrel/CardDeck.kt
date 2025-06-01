package com.copetti.dailyshuffle.scoundrel

import kotlin.random.Random

data class CardDeck(
    val cards: List<Card>
) {

    fun filtered(strategy: DeckFilteringStrategy): CardDeck {
        val filteredCards = cards.filter(strategy::filter)
        return CardDeck(filteredCards)
    }

    fun drawAtMost(count: Int): Pair<CardDeck, List<Card>> {
        return Pair(
            CardDeck(cards.drop(count)),
            cards.take(count)
        )
    }

    fun bottomRandomly(cards: List<Card>, random: Random) = CardDeck(this.cards + cards.shuffled(random))

    companion object {
        fun newShuffledDeck(random: Random): CardDeck {

            val shuffledDeck = mutableListOf<Card>()

            CardRank.entries.forEach { rank ->
                CardSuit.entries.forEach { suit ->
                    shuffledDeck.add(Card(rank, suit))
                }
            }

            return CardDeck(shuffledDeck.shuffled(random))
        }
    }
}

interface DeckFilteringStrategy {

    fun filter(card: Card): Boolean
}
