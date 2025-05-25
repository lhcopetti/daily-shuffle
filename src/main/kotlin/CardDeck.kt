package com.copetti.dailyshuffle.scoundrel

import java.util.*

class CardDeck(
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


    companion object {
        fun newShuffledDeck(seed: Long): CardDeck {

            val shuffledDeck = mutableListOf<Card>()

            CardRank.entries.forEach { rank ->
                CardSuit.entries.forEach { suit ->
                    shuffledDeck.add(Card(rank, suit))
                }
            }

            val random = Random(seed)
            return CardDeck(shuffledDeck.shuffled(random))
        }
    }
}

interface DeckFilteringStrategy {

    fun filter(card: Card): Boolean
}
