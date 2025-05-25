package com.copetti.dailyshuffle.scoundrel.com.copetti.dailyshuffle.scoundrel.state

import com.copetti.dailyshuffle.scoundrel.Card
import com.copetti.dailyshuffle.scoundrel.ScoundrelException
import com.copetti.dailyshuffle.scoundrel.ScoundrelGameView
import com.copetti.dailyshuffle.scoundrel.CardDeck
import com.copetti.dailyshuffle.scoundrel.com.copetti.dailyshuffle.scoundrel.ScoundrelDeckFilteringStrategy
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelRoom
import kotlin.random.Random

data class ScoundrelGameState(
    val deck: CardDeck,
    val room: ScoundrelRoom,
    val random: Random,
    val life: Int = STARTING_LIFE,
    val skippedLastRoom: Boolean = false,
    val drankPotionInRoom: Boolean = false
) : ScoundrelGameView {

    companion object {

        private const val ROOM_SIZE: Int = 4
        private const val STARTING_LIFE: Int = 20

        fun newGameState(deck: CardDeck, random: Random): ScoundrelGameState {
            val scoundrelDeck = deck.filtered(ScoundrelDeckFilteringStrategy())

            if (scoundrelDeck.cards.isEmpty())
                throw ScoundrelException("Scoundrel game cannot be started without any playing cards")

            val (dungeon, roomCards) = scoundrelDeck.drawAtMost(ROOM_SIZE)
            val room = ScoundrelRoom(roomCards)
            return ScoundrelGameState(dungeon, room, random)
        }

    }

    override fun dungeonSize() = deck.cards.size

    override fun room(): ScoundrelRoom {
        return room
    }

    override fun life(): Int {
        return life
    }

}