package com.copetti.dailyshuffle.scoundrel.command.factories

import com.copetti.dailyshuffle.scoundrel.Card
import com.copetti.dailyshuffle.scoundrel.CardDeck
import com.copetti.dailyshuffle.scoundrel.CardRank
import com.copetti.dailyshuffle.scoundrel.CardSuit
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import com.copetti.dailyshuffle.scoundrel.command.commands.DiscardHealthPotionCommand
import com.copetti.dailyshuffle.scoundrel.command.commands.DrinkPotionCommand
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelRoom
import org.junit.jupiter.api.Assertions.*
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
            life = 6
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
            life = 10,
            drankPotionInRoom = true
        )
        val commands = factory.createCommands(state)
        val discardCommands = commands.filterIsInstance<DiscardHealthPotionCommand>()
        assertEquals(2, discardCommands.size)
        assertTrue(discardCommands.any { it.displayName().contains("#0") })
        assertTrue(discardCommands.any { it.displayName().contains("#1") })
    }

    @Test
    fun `caps life bonus at STARTING_LIFE when creating potion command`() {
        val room = ScoundrelRoom.buildScoundrelRoom(
            listOf(
                Card(CardRank.ACE, CardSuit.HEARTS) // potion with value 14
            )
        )
        val state = ScoundrelGameState(
            deck = CardDeck(emptyList()),
            room = room,
            life = 15 // Current life is 15, potion would heal 14, but should cap at 20
        )
        val commands = factory.createCommands(state)
        assertEquals(1, commands.size)
        val potionCommand = commands[0] as DrinkPotionCommand
        assertTrue(potionCommand.displayName().contains("5")) // Should show +5 life (to reach cap of 20) instead of +14
    }

    @Test
    fun `creates potion command with no life bonus when at full life`() {
        val room = ScoundrelRoom.buildScoundrelRoom(
            listOf(
                Card(CardRank.FIVE, CardSuit.HEARTS) // potion with value 5
            )
        )
        val state = ScoundrelGameState(
            deck = CardDeck(emptyList()),
            room = room,
            life = ScoundrelGameState.STARTING_LIFE // Already at max life
        )
        val commands = factory.createCommands(state)
        assertEquals(1, commands.size)
        val potionCommand = commands[0] as DrinkPotionCommand
        assertTrue(potionCommand.displayName().contains("0")) // Should show +0 life since already at max
    }
}