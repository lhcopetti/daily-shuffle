package com.copetti.dailyshuffle.scoundrel.command.commands

import com.copetti.dailyshuffle.scoundrel.command.ScoundrelTarget
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelTargetCommand
import com.copetti.dailyshuffle.scoundrel.engine.ScoundrelPotion
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState

class DrinkPotionCommand(
    private val lifeBonus: Int,
    target: ScoundrelTarget<ScoundrelPotion>
) : ScoundrelTargetCommand<ScoundrelPotion>(target) {

    override fun doExecute(state: ScoundrelGameState): ScoundrelGameState {
        return state.copy(
            life = state.life + lifeBonus,
            drankPotionInRoom = true
        )
    }

    override fun displayName(): String = "Drink Potion at #${target.index} - (+$lifeBonus life)"
}