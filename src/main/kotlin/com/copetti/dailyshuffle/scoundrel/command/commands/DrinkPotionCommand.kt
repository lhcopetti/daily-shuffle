package com.copetti.dailyshuffle.scoundrel.command.commands

import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelTargetCommand
import kotlin.math.min

class DrinkPotionCommand(
    private val lifeBonus: Int,
    target: Int
) : ScoundrelTargetCommand(target) {

    override fun doExecute(state: ScoundrelGameState): ScoundrelGameState {
        val newLife = min(state.life + lifeBonus, ScoundrelGameState.STARTING_LIFE)
        return state.copy(
            life = newLife,
            drankPotionInRoom = true
        )
    }

    override fun displayName(): String = "Drink Potion at #$target - (+$lifeBonus life)"
} 