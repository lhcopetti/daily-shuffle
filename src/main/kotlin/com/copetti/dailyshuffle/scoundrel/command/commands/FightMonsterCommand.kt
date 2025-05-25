package com.copetti.dailyshuffle.scoundrel.command.commands

import com.copetti.dailyshuffle.scoundrel.com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelTargetCommand

class FightMonsterCommand(
    private val damage: Int,
    target: Int
) : ScoundrelTargetCommand(target) {
    override fun doExecute(state: ScoundrelGameState): ScoundrelGameState {
        return state.copy(
            life = state.life - damage
        )
    }

    override fun displayName(): String = "Fight monster at #$target - (-$damage life)"
} 