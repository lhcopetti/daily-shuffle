package com.copetti.dailyshuffle.scoundrel.command.commands

import com.copetti.dailyshuffle.scoundrel.command.ScoundrelTarget
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelTargetCommand
import com.copetti.dailyshuffle.scoundrel.engine.ScoundrelWeapon
import com.copetti.dailyshuffle.scoundrel.state.EquippedWeapon
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState

class EquipWeaponCommand(
    target: ScoundrelTarget<ScoundrelWeapon>
) : ScoundrelTargetCommand<ScoundrelWeapon>(target) {

    override fun doExecute(state: ScoundrelGameState): ScoundrelGameState {
        return state.copy(
            equippedWeapon = EquippedWeapon(
                attack = target.value.attackBonus,
                monstersSlain = listOf()
            )
        )
    }

    override fun displayName(): String = "Equip ${target.value} at #${target.index} - (+${target.value.attackBonus} attack)"
} 