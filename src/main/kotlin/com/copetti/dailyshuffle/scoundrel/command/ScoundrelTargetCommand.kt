package com.copetti.dailyshuffle.scoundrel.command

import com.copetti.dailyshuffle.scoundrel.engine.ScoundrelCard
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState


data class ScoundrelTarget<T: ScoundrelCard>(
    val index: Int,
    val value: T
)

abstract class  ScoundrelTargetCommand<T: ScoundrelCard>(
    val target: ScoundrelTarget<T>
) : ScoundrelCommand {

    abstract fun doExecute(state: ScoundrelGameState): ScoundrelGameState

    final override fun execute(state: ScoundrelGameState): ScoundrelGameState {
        val childState = doExecute(state)
        return applyTargetCommand(childState)
    }

    private fun applyTargetCommand(state: ScoundrelGameState): ScoundrelGameState {
        val newRoom = state.room.clear(target.index)
        return state.copy(room = newRoom)
    }

}