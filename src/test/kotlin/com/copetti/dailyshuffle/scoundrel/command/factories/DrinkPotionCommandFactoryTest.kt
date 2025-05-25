package com.copetti.dailyshuffle.scoundrel.command.factories

import com.copetti.dailyshuffle.scoundrel.Card
import com.copetti.dailyshuffle.scoundrel.CardDeck
import com.copetti.dailyshuffle.scoundrel.CardRank
import com.copetti.dailyshuffle.scoundrel.CardSuit
import com.copetti.dailyshuffle.scoundrel.com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import com.copetti.dailyshuffle.scoundrel.command.commands.DiscardHealthPotionCommand
import com.copetti.dailyshuffle.scoundrel.command.commands.DrinkPotionCommand
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelRoom
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.random.Random

class DrinkPotionCommandFactoryTest {
    private val factory = DrinkPotionCommandFactory()

    @Test
    fun `creates command for each potion card in the room`() {
        val room = ScoundrelRoom.buildScoundrelRoom(
            listOf(
                Card(CardRank.ACE, CardSuit.HEARTS), // potion
                Card(CardRank.FIVE, CardSuit.CLUBS), // not potion
                Card(CardRank.TEN, CardSuit.HEARTS) // potion
            )
        )
        val state = ScoundrelGameState(
            deck = CardDeck(emptyList()),
            room = room,
            random = Random(1),
            life = 10
        )
        val commands = factory.createCommands(state)
        assertEquals(2, commands.size)
        val potionCommands = commands.filterIsInstance<DrinkPotionCommand>()
        assertEquals(2, potionCommands.size)
        assertTrue(potionCommands.any { it.displayName().contains("#0") && it.displayName().contains("14") })
        assertTrue(potionCommands.any { it.displayName().contains("#2") && it.displayName().contains("10") })
    }

    @Test
    fun `does not create command for non-potion cards`() {
        val room = ScoundrelRoom.buildScoundrelRoom(
            listOf(
                Card(CardRank.FIVE, CardSuit.CLUBS),
                Card(CardRank.TEN, CardSuit.DIAMONDS)
            )
        )
        val state = ScoundrelGameState(
            deck = CardDeck(emptyList()),
            room = room,
            random = Random(1),
            life = 10
        )
        val commands = factory.createCommands(state)
        assertTrue(commands.isEmpty())
    }

    @Test
    fun `creates discard command for each potion card if a potion has already been drunk in this room`() {
        val room = ScoundrelRoom.buildScoundrelRoom(
            listOf(
                Card(CardRank.ACE, CardSuit.HEARTS),
                Card(CardRank.TEN, CardSuit.HEARTS)
            )
        )
        val state = ScoundrelGameState(
            deck = CardDeck(emptyList()),
            room = room,
            random = Random(1),
            life = 10,
            drankPotionInRoom = true
        )
        val commands = factory.createCommands(state)
        val discardCommands = commands.filterIsInstance<DiscardHealthPotionCommand>()
        assertEquals(2, discardCommands.size)
        assertTrue(discardCommands.any { it.displayName().contains("#0") })
        assertTrue(discardCommands.any { it.displayName().contains("#1") })
    }
}