package com.copetti.dailyshuffle.scoundrel.command.factories

import com.copetti.dailyshuffle.scoundrel.Card
import com.copetti.dailyshuffle.scoundrel.CardDeck
import com.copetti.dailyshuffle.scoundrel.CardRank
import com.copetti.dailyshuffle.scoundrel.CardSuit
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import com.copetti.dailyshuffle.scoundrel.command.commands.SkipRoomCommand
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelRoom
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.random.Random

class SkipRoomCommandFactoryTest {
    private val factory = SkipRoomCommandFactory()

    @Test
    fun `creates skip command when room is full`() {
        val room = ScoundrelRoom.buildScoundrelRoom(
            listOf(
                Card(CardRank.ACE, CardSuit.CLUBS),
                Card(CardRank.FIVE, CardSuit.HEARTS),
                Card(CardRank.KING, CardSuit.SPADES),
                Card(CardRank.TEN, CardSuit.DIAMONDS)
            )
        )
        val state = ScoundrelGameState(
            deck = CardDeck(emptyList()),
            room = room,
            random = Random(1),
            life = 20
        )
        val commands = factory.createCommands(state)
        assertEquals(1, commands.size)
        assertTrue(commands[0] is SkipRoomCommand)
    }

    @Test
    fun `does not create skip command when room has empty sectors`() {
        val room = ScoundrelRoom.buildScoundrelRoom(
            listOf(
                Card(CardRank.ACE, CardSuit.CLUBS),
                null,
                Card(CardRank.KING, CardSuit.SPADES),
                Card(CardRank.TEN, CardSuit.DIAMONDS)
            )
        )
        val state = ScoundrelGameState(
            deck = CardDeck(emptyList()),
            room = room,
            random = Random(1),
            life = 20
        )
        val commands = factory.createCommands(state)
        assertTrue(commands.isEmpty())
    }

    @Test
    fun `does not create skip command when last room was skipped`() {
        val room = ScoundrelRoom.buildScoundrelRoom(
            listOf(
                Card(CardRank.ACE, CardSuit.CLUBS),
                Card(CardRank.FIVE, CardSuit.HEARTS),
                Card(CardRank.KING, CardSuit.SPADES),
                Card(CardRank.TEN, CardSuit.DIAMONDS)
            )
        )
        val state = ScoundrelGameState(
            deck = CardDeck(emptyList()),
            room = room,
            random = Random(1),
            life = 20,
            skippedLastRoom = true
        )
        val commands = factory.createCommands(state)
        assertTrue(commands.isEmpty())
    }
}