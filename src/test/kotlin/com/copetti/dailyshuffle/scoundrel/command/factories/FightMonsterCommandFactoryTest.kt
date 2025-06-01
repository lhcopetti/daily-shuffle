package com.copetti.dailyshuffle.scoundrel.command.factories

import com.copetti.dailyshuffle.scoundrel.Card
import com.copetti.dailyshuffle.scoundrel.CardDeck
import com.copetti.dailyshuffle.scoundrel.CardRank
import com.copetti.dailyshuffle.scoundrel.CardSuit
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import com.copetti.dailyshuffle.scoundrel.command.commands.FightMonsterCommand
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelRoom
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.random.Random

class FightMonsterCommandFactoryTest {
    @Test
    fun `creates command for each monster in the room with correct damage and target`() {
        val cards = listOf(
            Card(CardRank.ACE, CardSuit.CLUBS), // monster (damage 14)
            Card(CardRank.FIVE, CardSuit.HEARTS), // not monster
            Card(CardRank.KING, CardSuit.SPADES), // monster (damage 13)
            Card(CardRank.TEN, CardSuit.DIAMONDS) // not monster
        )
        val room = ScoundrelRoom.buildScoundrelRoom(cards)
        val state = ScoundrelGameState(
            deck = CardDeck(emptyList()),
            room = room,
            life = 20
        )
        val commands = FightMonsterCommandFactory().createCommands(state)
        val fightCommands = commands.filterIsInstance<FightMonsterCommand>()
        assertEquals(2, fightCommands.size)
        assertTrue(fightCommands.any { it.displayName().contains("#0") && it.displayName().contains("14") })
        assertTrue(fightCommands.any { it.displayName().contains("#2") && it.displayName().contains("13") })
    }
}