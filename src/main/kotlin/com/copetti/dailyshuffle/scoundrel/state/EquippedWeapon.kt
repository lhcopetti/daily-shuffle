package com.copetti.dailyshuffle.scoundrel.state

import com.copetti.dailyshuffle.scoundrel.Card
import com.copetti.dailyshuffle.scoundrel.engine.ScoundrelCardMapper

data class EquippedWeapon (
    val attack: Int,
    private val monstersSlain: List<Card>
) {

    fun durability(): Int? {
        if (monstersSlain.isEmpty())
            return null

        val scoundrelCard = ScoundrelCardMapper.toScoundrelCard(monstersSlain.last())
        return scoundrelCard.value
    }
}