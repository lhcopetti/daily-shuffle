package com.copetti.dailyshuffle.scoundrel.engine

import com.copetti.dailyshuffle.scoundrel.Card
import com.copetti.dailyshuffle.scoundrel.CardRank
import com.copetti.dailyshuffle.scoundrel.CardSuit

enum class ScoundrelType {
    POTION,
    ENEMY,
    EQUIPMENT
}

data class ScoundrelCard(
    val type: ScoundrelType,
    val value: Int
)

class ScoundrelCardMapper {

    companion object {
        fun toScoundrelCard(card: Card): ScoundrelCard {

            val type = when (card.suit) {
                CardSuit.CLUBS -> ScoundrelType.ENEMY
                CardSuit.SPADES -> ScoundrelType.ENEMY
                CardSuit.HEARTS -> ScoundrelType.POTION
                CardSuit.DIAMONDS -> ScoundrelType.EQUIPMENT
            }
            val value = when (card.rank) {
                CardRank.ACE -> 14
                CardRank.TWO -> 2
                CardRank.THREE -> 3
                CardRank.FOUR -> 4
                CardRank.FIVE -> 5
                CardRank.SIX -> 6
                CardRank.SEVEN -> 7
                CardRank.EIGHT -> 8
                CardRank.NINE -> 9
                CardRank.TEN -> 10
                CardRank.JACK -> 11
                CardRank.QUEEN -> 12
                CardRank.KING -> 13
            }

            return ScoundrelCard(type, value)
        }
    }
}
