package com.copetti.dailyshuffle.scoundrel.command.commands

import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelTargetCommand
import com.copetti.dailyshuffle.scoundrel.state.EquippedWeapon

class EquipWeaponCommand(
    private val attackBonus: Int,
    target: Int
) : ScoundrelTargetCommand(target) {

    override fun doExecute(state: ScoundrelGameState): ScoundrelGameState {
        return state.copy(
            equippedWeapon = EquippedWeapon(
                attack = attackBonus,
                monstersSlain = listOf()
            )
        )
    }

    override fun displayName(): String = "Equip Weapon at #$target - (+$attackBonus attack)"
} 