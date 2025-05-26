package com.copetti.dailyshuffle.scoundrel.command.commands

import com.copetti.dailyshuffle.scoundrel.command.ScoundrelTarget
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelTargetCommand
import com.copetti.dailyshuffle.scoundrel.engine.ScoundrelPotion
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState

class DiscardHealthPotionCommand(target: ScoundrelTarget<ScoundrelPotion>) :
    ScoundrelTargetCommand<ScoundrelPotion>(target) {
    override fun doExecute(state: ScoundrelGameState): ScoundrelGameState {
        return state
    }

    override fun displayName(): String = "Discard Potion at #${target.index}"
} 