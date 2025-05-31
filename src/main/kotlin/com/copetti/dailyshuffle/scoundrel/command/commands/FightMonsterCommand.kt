package com.copetti.dailyshuffle.scoundrel.command.commands

import com.copetti.dailyshuffle.scoundrel.command.ScoundrelTarget
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelTargetCommand
import com.copetti.dailyshuffle.scoundrel.engine.ScoundrelMonster
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState

class FightMonsterCommand(
    target: ScoundrelTarget<ScoundrelMonster>
) : ScoundrelTargetCommand<ScoundrelMonster>(target) {
    override fun doExecute(state: ScoundrelGameState): ScoundrelGameState {
        return state.copy(
            life = state.life - target.value.attackPower
        )
    }

    override fun displayName(): String = "Fight ${target.value} at #${target.index} - (-${target.value.attackPower} life)"
} 