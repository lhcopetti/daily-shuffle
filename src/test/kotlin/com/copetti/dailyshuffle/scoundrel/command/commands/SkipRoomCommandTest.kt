package com.copetti.dailyshuffle.scoundrel.command.commands

import com.copetti.dailyshuffle.scoundrel.Card
import com.copetti.dailyshuffle.scoundrel.CardDeck
import com.copetti.dailyshuffle.scoundrel.CardRank
import com.copetti.dailyshuffle.scoundrel.CardSuit
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelRoom
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SkipRoomCommandTest {

    @Test
    fun `skip room moves current room cards to bottom of deck`() {
        val roomCards = listOf(
            Card(CardRank.ACE, CardSuit.CLUBS),
            Card(CardRank.TWO, CardSuit.CLUBS),
            Card(CardRank.THREE, CardSuit.CLUBS),
            Card(CardRank.FOUR, CardSuit.CLUBS)
        )
        val deckCards = listOf(
            Card(CardRank.FIVE, CardSuit.SPADES),
            Card(CardRank.SIX, CardSuit.SPADES),
            Card(CardRank.SEVEN, CardSuit.SPADES),
            Card(CardRank.EIGHT, CardSuit.SPADES)
        )

        val state = createGameState(roomCards, deckCards)
        val command = SkipRoomCommand()

        val newState = command.execute(state)

        // Verify old room cards are in the deck
        assertTrue(newState.deck.cards.containsAll(roomCards), "Old room cards should be in deck")

        // Verify new room has the cards that were at the top of the deck
        assertEquals(deckCards.toSet(), newState.room.getCards().toSet(), "New room should have former top deck cards")

        assertTrue(newState.skippedLastRoom, "skippedLastRoom should be true after skipping")
    }

    @Test
    fun `skip command when deck of cards has less cards than required to fill the room`() {
        val roomCards = listOf(
            Card(CardRank.ACE, CardSuit.CLUBS),
            Card(CardRank.TWO, CardSuit.CLUBS),
            Card(CardRank.THREE, CardSuit.CLUBS),
            Card(CardRank.FOUR, CardSuit.CLUBS)
        )
        val deckCards = listOf(
            Card(CardRank.FIVE, CardSuit.SPADES),
            Card(CardRank.SIX, CardSuit.SPADES)
        )
        val state = createGameState(roomCards, deckCards)
        val command = SkipRoomCommand()

        val newState = command.execute(state)

        assertTrue(newState.room.getCards().containsAll(deckCards), "Cards that should be in the room")
        assertEquals(
            2,
            roomCards.toSet().intersect(newState.room.getCards()).size,
            "Two cards from the current room should be in the next"
        )
    }

    private fun createGameState(
        roomCards: List<Card>,
        deckCards: List<Card>,
    ): ScoundrelGameState {
        return ScoundrelGameState(
            deck = CardDeck(deckCards),
            room = ScoundrelRoom.buildScoundrelRoom(roomCards),
        )
    }
} 