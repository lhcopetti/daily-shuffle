package com.copetti.dailyshuffle.scoundrel.command.commands.internal

import com.copetti.dailyshuffle.scoundrel.*
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelRoom
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import kotlin.random.Random

class ReloadRoomCommandTest {
    @Test
    fun `reload fills empty slots in the room with new cards from the deck`() {
        val room = ScoundrelRoom.buildScoundrelRoom(
            listOf(
                Card(CardRank.ACE, CardSuit.CLUBS),
                null,
                Card(CardRank.FIVE, CardSuit.HEARTS),
                null
            )
        )
        val deckCards = listOf(
            Card(CardRank.KING, CardSuit.SPADES),
            Card(CardRank.TEN, CardSuit.DIAMONDS)
        )
        val state = ScoundrelGameState(
            deck = CardDeck(deckCards),
            room = room,
            life = 20,
            drankPotionInRoom = true,
            skippedLastRoom = true
        )
        val command = ReloadRoomCommand()
        val newState = command.execute(state)

        assertEquals(4, newState.room.sectors.size)
        assertEquals(4, newState.room.getCards().size)
        assertEquals(0, newState.deck.cards.size)
        assertFalse(newState.drankPotionInRoom)
        assertFalse(newState.skippedLastRoom)
    }

    @Test
    fun `reload only picks remaining cards from deck and does not throw if not enough cards`() {
        val room = ScoundrelRoom.buildScoundrelRoom(
            listOf(
                Card(CardRank.ACE, CardSuit.CLUBS),
                null,
                Card(CardRank.FIVE, CardSuit.HEARTS),
                null
            )
        )
        val deckCards = listOf(
            Card(CardRank.KING, CardSuit.SPADES) // Only one card for two empty slots
        )
        val state = ScoundrelGameState(
            deck = CardDeck(deckCards),
            room = room,
            life = 20,
            drankPotionInRoom = true,
            skippedLastRoom = true
        )
        val command = ReloadRoomCommand()
        val newState = command.execute(state)

        // Room should now have 3 cards (one slot still empty)
        assertEquals(4, newState.room.sectors.size)
        assertEquals(3, newState.room.getCards().size)
        assertEquals(0, newState.deck.cards.size)
        assertFalse(newState.drankPotionInRoom)
        assertFalse(newState.skippedLastRoom)
    }
} 