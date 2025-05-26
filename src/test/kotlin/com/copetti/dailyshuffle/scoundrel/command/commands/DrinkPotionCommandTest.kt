package com.copetti.dailyshuffle.scoundrel.command.commands

import com.copetti.dailyshuffle.scoundrel.Card
import com.copetti.dailyshuffle.scoundrel.CardDeck
import com.copetti.dailyshuffle.scoundrel.CardRank
import com.copetti.dailyshuffle.scoundrel.CardSuit
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelRoom
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.random.Random

class DrinkPotionCommandTest {

    @Test
    fun `drinking potion increases life by potion value`() {
        val state = ScoundrelGameState(
            deck = CardDeck(emptyList()),
            room = ScoundrelRoom.buildScoundrelRoom(listOf(Card(CardRank.FIVE, CardSuit.HEARTS))),
            random = Random(1),
            life = 10
        )
        
        val command = DrinkPotionCommand(lifeBonus = 5, target = 0)
        val newState = command.execute(state)
        
        assertEquals(15, newState.life)
        assertTrue(newState.drankPotionInRoom)
    }

    @Test
    fun `drinking potion cannot increase life above starting life`() {
        val state = ScoundrelGameState(
            deck = CardDeck(emptyList()),
            room = ScoundrelRoom.buildScoundrelRoom(listOf(Card(CardRank.ACE, CardSuit.HEARTS))),
            random = Random(1),
            life = 15
        )
        
        val command = DrinkPotionCommand(lifeBonus = 14, target = 0)
        val newState = command.execute(state)
        
        assertEquals(20, newState.life) // Should cap at STARTING_LIFE (20)
        assertTrue(newState.drankPotionInRoom)
    }

    @Test
    fun `drinking potion at full life does not increase life further`() {
        val state = ScoundrelGameState(
            deck = CardDeck(emptyList()),
            room = ScoundrelRoom.buildScoundrelRoom(listOf(Card(CardRank.FIVE, CardSuit.HEARTS))),
            random = Random(1),
            life = 20
        )
        
        val command = DrinkPotionCommand(lifeBonus = 5, target = 0)
        val newState = command.execute(state)
        
        assertEquals(20, newState.life)
        assertTrue(newState.drankPotionInRoom)
    }
} 