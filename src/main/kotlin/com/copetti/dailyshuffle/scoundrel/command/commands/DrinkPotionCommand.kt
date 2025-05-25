package com.copetti.dailyshuffle.scoundrel.command.commands

import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommand
import com.copetti.dailyshuffle.scoundrel.com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState

class DrinkPotionCommand : ScoundrelCommand {
    override fun execute(state: ScoundrelGameState): ScoundrelGameState {
        return state.copy(life = state.life + 5)
    }

    override fun displayName(): String = "Drink Potion (+5 Life)"
} 