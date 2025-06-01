package com.copetti.dailyshuffle.scoundrel.engine

import com.copetti.dailyshuffle.scoundrel.*
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommand
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelRoom
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.random.Random

class ScoundrelGameEngineTest {

    @Test
    fun `executing a command increments game round`() {
        val engine = ScoundrelGameEngine()
        val initialState = createGameState()
        val command = createDummyCommand()

        val newState = engine.executeCommand(initialState, command)

        assertEquals(1, newState.roundCount, "Round count should increment after executing command")
    }

    @Test
    fun `executing multiple commands increments game round sequentially`() {
        val engine = ScoundrelGameEngine()
        var state = createGameState()
        val command = createDummyCommand()

        repeat(3) { expectedRound ->
            state = engine.executeCommand(state, command)
            assertEquals(expectedRound + 1, state.roundCount, "Round count should be ${expectedRound + 1} after ${expectedRound + 1} commands")
        }
    }

    @Test
    fun `reloading room after last card does not increment round count`() {
        val engine = ScoundrelGameEngine()
        val initialState = createGameStateWithTwoCardsToTriggerRoomReloading()
        val command = createDummyCommand()

        val afterCommand = engine.executeCommand(initialState, command)
        
        // The round count should only increment once, even though the room is reloaded
        assertEquals(1, afterCommand.roundCount, "Round count should only increment once even with room reload")
    }

    private fun createGameState(): ScoundrelGameState {
        val deck = CardDeck.newShuffledDeck(Random(42))
        return ScoundrelGameState.newGameState(deck, Random(42))
    }

    private fun createGameStateWithTwoCardsToTriggerRoomReloading(): ScoundrelGameState {
        val cards = listOf(
            Card(CardRank.ACE, CardSuit.CLUBS),
            Card(CardRank.TWO, CardSuit.CLUBS),
        )
        val room = ScoundrelRoom.buildScoundrelRoom(cards)
        return ScoundrelGameState(
            deck = CardDeck(emptyList()),
            room = room,
            random = Random(42)
        )
    }

    private fun createDummyCommand() = object : ScoundrelCommand {
        override fun execute(state: ScoundrelGameState): ScoundrelGameState {
            return state
        }
        override fun displayName() = "Dummy Command"
    }
} 