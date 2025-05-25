package com.copetti.dailyshuffle.scoundrel.command.commands

import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelTargetCommand

class DiscardHealthPotionCommand(target: Int) : ScoundrelTargetCommand(target) {
    override fun doExecute(state: ScoundrelGameState): ScoundrelGameState {
        return state
    }

    override fun displayName(): String = "Discard Potion at #$target"
} 