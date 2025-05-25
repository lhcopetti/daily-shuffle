package com.copetti.dailyshuffle.scoundrel.state

import com.copetti.dailyshuffle.scoundrel.Card
import com.copetti.dailyshuffle.scoundrel.engine.ScoundrelCardMapper

data class ScoundrelRoomSector(
    val roomIndex: Int,
    val card: Card?
) {

    fun toScoundrelCard() = card?.let { ScoundrelCardMapper.toScoundrelCard(it) }

    fun empty() = copy(card = null)
}