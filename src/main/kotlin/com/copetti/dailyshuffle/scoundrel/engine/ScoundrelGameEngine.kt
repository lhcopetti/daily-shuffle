package com.copetti.dailyshuffle.scoundrel.engine

import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommand
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommandFactory
import com.copetti.dailyshuffle.scoundrel.command.commands.internal.ReloadRoomCommand
import com.copetti.dailyshuffle.scoundrel.command.factories.*
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameStatus

class ScoundrelGameEngine(
) {
    private val commandFactories: List<ScoundrelCommandFactory> = listOf(
        SkipRoomCommandFactory(),
        DrinkPotionCommandFactory(),
        FightMonsterCommandFactory(),
        EquipWeaponCommandFactory(),
        FightMonsterWithWeaponCommandFactory()
    )

    fun getAvailableMoves(state: ScoundrelGameState): List<ScoundrelCommand> {
        if (state.gameStatus() != ScoundrelGameStatus.IN_PROGRESS)
            throw IllegalStateException("The game has already ended: ${state.gameStatus()}")

        return commandFactories.flatMap { f -> f.createCommands(state) }
    }

    fun executeCommand(state: ScoundrelGameState, command: ScoundrelCommand): ScoundrelGameState {
        if (state.gameStatus() != ScoundrelGameStatus.IN_PROGRESS)
            throw IllegalStateException("The game has already ended: ${state.gameStatus()}")

        return advanceState(command.execute(state))
    }

    private fun advanceState(state: ScoundrelGameState): ScoundrelGameState {
        if (onlyOneRemainingCardInRoom(state))
            return ReloadRoomCommand().execute(state)

        return state
    }

    private fun onlyOneRemainingCardInRoom(state: ScoundrelGameState) = state.room.getCards().size == 1
}