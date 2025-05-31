package com.copetti.dailyshuffle.scoundrel.engine

import com.copetti.dailyshuffle.scoundrel.Card
import com.copetti.dailyshuffle.scoundrel.CardRank
import com.copetti.dailyshuffle.scoundrel.CardSuit

sealed interface ScoundrelCard

data class ScoundrelMonster(
    val attackPower: Int
): ScoundrelCard {
    override fun toString() = "Monster($attackPower)"
}

data class ScoundrelWeapon(
    val attackBonus: Int
): ScoundrelCard {
    override fun toString() = "Weapon($attackBonus)"
}

data class ScoundrelPotion(
    val lifeBonus: Int
): ScoundrelCard {
    override fun toString() = "Potion($lifeBonus)"
}

class ScoundrelCardMapper {

    companion object {

        fun toScoundrelCard(card: Card): ScoundrelCard {

            val value = convertRank(card.rank)
            return when (card.suit) {
                CardSuit.CLUBS, CardSuit.SPADES -> ScoundrelMonster(value)
                CardSuit.HEARTS -> ScoundrelPotion(value)
                CardSuit.DIAMONDS -> ScoundrelWeapon(value)
            }
        }

        private fun convertRank(rank: CardRank): Int {
            return when (rank) {
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
        }
    }
}
