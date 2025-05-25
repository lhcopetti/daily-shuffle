package com.copetti.dailyshuffle.scoundrel.command.commands

import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelTargetCommand

class DrinkPotionCommand(
    private val lifeBonus: Int,
    target: Int
) : ScoundrelTargetCommand(target) {

    override fun doExecute(state: ScoundrelGameState): ScoundrelGameState {
        return state.copy(
            life = state.life + lifeBonus,
            drankPotionInRoom = true
        )
    }

    override fun displayName(): String = "Drink Potion at #$target - (+$lifeBonus life)"
} 