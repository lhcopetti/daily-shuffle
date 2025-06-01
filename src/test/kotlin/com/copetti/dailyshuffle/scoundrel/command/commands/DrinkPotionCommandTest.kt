package com.copetti.dailyshuffle.scoundrel.command.commands

import com.copetti.dailyshuffle.scoundrel.Card
import com.copetti.dailyshuffle.scoundrel.CardDeck
import com.copetti.dailyshuffle.scoundrel.CardRank
import com.copetti.dailyshuffle.scoundrel.CardSuit
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelTarget
import com.copetti.dailyshuffle.scoundrel.engine.ScoundrelPotion
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelRoom
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.random.Random

class DrinkPotionCommandTest {

    @Test
    fun `drinking potion increases life by potion value`() {
        val state = ScoundrelGameState(
            deck = CardDeck(emptyList()),
            room = ScoundrelRoom.buildScoundrelRoom(listOf(Card(CardRank.FIVE, CardSuit.HEARTS))),
            life = 10
        )

        val command = DrinkPotionCommand(
            lifeBonus = 5,
            target = ScoundrelTarget(index = 0, value = ScoundrelPotion(lifeBonus = 5))
        )
        val newState = command.execute(state)

        assertEquals(15, newState.life)
        assertTrue(newState.drankPotionInRoom)
    }

}