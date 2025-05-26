package com.copetti.dailyshuffle.scoundrel.command.commands

import com.copetti.dailyshuffle.scoundrel.Card
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelTargetCommand

class FightMonsterWithWeaponCommand(
    private val damage: Int,
    private val monster: Card,
    target: Int
) : ScoundrelTargetCommand(target) {
    override fun doExecute(state: ScoundrelGameState): ScoundrelGameState {
        return state.copy(
            life = state.life - damage,
            equippedWeapon = state.equippedWeapon?.slay(monster)
        )
    }

    override fun displayName(): String = "Fight monster with equipped weapon at #$target - (-$damage life)"
} 