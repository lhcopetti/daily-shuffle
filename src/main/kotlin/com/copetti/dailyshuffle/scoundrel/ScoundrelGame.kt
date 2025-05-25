package com.copetti.dailyshuffle.scoundrel

import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommand
import com.copetti.dailyshuffle.scoundrel.engine.ScoundrelGameEngine
import kotlin.random.Random

class ScoundrelGame private constructor(
    private val state: ScoundrelGameState,
    private val engine: ScoundrelGameEngine
) : ScoundrelGameView {

    override fun dungeonSize() = state.dungeonSize()

    override fun room() = state.room

    override fun life() = state.life

    fun getAvailableCommands(): List<ScoundrelCommand> {
        return engine.getAvailableMoves(state)
    }

    fun executeCommand(command: ScoundrelCommand): ScoundrelGame {
        return ScoundrelGame(engine.executeCommand(state, command), engine)
    }

    companion object {

        fun newGame(random: Random, deck: CardDeck): ScoundrelGame {
            val state = ScoundrelGameState.newGameState(deck, random)
            return ScoundrelGame(
                state = state,
                engine = ScoundrelGameEngine()
            )
        }

    }
}

class ScoundrelDeckFilteringStrategy : DeckFilteringStrategy {
    override fun filter(card: Card): Boolean {
        if (card.suit.color == CardColor.RED && card.rank.isFaceCard())
            return false

        if (card.suit.color == CardColor.RED && card.rank == CardRank.ACE)
            return false

        return true
    }

}