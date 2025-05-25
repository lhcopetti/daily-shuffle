package com.copetti.dailyshuffle.scoundrel.command

import com.copetti.dailyshuffle.scoundrel.com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState

abstract class ScoundrelTargetCommand(
    val target: Int
) : ScoundrelCommand {

    abstract fun doExecute(state: ScoundrelGameState): ScoundrelGameState

    final override fun execute(state: ScoundrelGameState): ScoundrelGameState {
        val childState = doExecute(state)
        return applyTargetCommand(childState)
    }

    private fun applyTargetCommand(state: ScoundrelGameState): ScoundrelGameState {
        val newRoom = state.room.clear(target)
        return state.copy(room = newRoom)
    }

}