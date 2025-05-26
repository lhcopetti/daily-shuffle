package com.copetti.dailyshuffle.scoundrel.state

import com.copetti.dailyshuffle.scoundrel.engine.ScoundrelMonster

data class EquippedWeapon(
    val attack: Int,
    private val monstersSlain: List<ScoundrelMonster>
) {

    fun durability() = monstersSlain.lastOrNull()?.attackPower

    fun slay(monster: ScoundrelMonster) = copy(
        monstersSlain = monstersSlain + listOf(monster)
    )

}