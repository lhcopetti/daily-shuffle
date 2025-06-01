package com.copetti.dailyshuffle.scoundrel

import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommand
import com.copetti.dailyshuffle.scoundrel.engine.ScoundrelGameEngine
import kotlin.random.Random

class ScoundrelGame private constructor(
    val state: ScoundrelGameState,
    private val engine: ScoundrelGameEngine,
    private val random: Random,
) : ScoundrelGameView {
    override fun gameStatus() = state.gameStatus()
    override fun gameRound() = state.gameRound()

    override fun dungeonSize() = state.dungeonSize()

    override fun room() = state.room

    override fun life() = state.life

    override fun equippedWeapon() = state.equippedWeapon()

    fun getAvailableCommands(): List<ScoundrelCommand> {
        return engine.getAvailableMoves(state)
    }

    fun executeCommand(command: ScoundrelCommand): ScoundrelGame {
        return ScoundrelGame(engine.executeCommand(state, command), engine,  random)
    }

    companion object {

        fun newGame(random: Random, deck: CardDeck, startingLife: Int? = null): ScoundrelGame {
            val state = ScoundrelGameState.newGameState(deck, startingLife)
            return ScoundrelGame(
                state = state,
                engine = ScoundrelGameEngine(),
                random = random
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