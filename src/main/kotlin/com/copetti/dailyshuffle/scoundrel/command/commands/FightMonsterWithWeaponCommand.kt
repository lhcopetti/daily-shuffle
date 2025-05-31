package com.copetti.dailyshuffle.scoundrel.command.commands

import com.copetti.dailyshuffle.scoundrel.command.ScoundrelTarget
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelTargetCommand
import com.copetti.dailyshuffle.scoundrel.engine.ScoundrelMonster

class FightMonsterWithWeaponCommand(
    private val damage: Int,
    target: ScoundrelTarget<ScoundrelMonster>
) : ScoundrelTargetCommand<ScoundrelMonster>(target) {
    override fun doExecute(state: ScoundrelGameState): ScoundrelGameState {
        return state.copy(
            life = state.life - damage,
            equippedWeapon = state.equippedWeapon?.slay(target.value)
        )
    }

    override fun displayName(): String = "Fight ${target.value} with equipped weapon at #${target.index} - (-$damage life)"
} 