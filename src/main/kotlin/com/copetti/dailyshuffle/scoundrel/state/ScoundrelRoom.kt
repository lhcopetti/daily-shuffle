package com.copetti.dailyshuffle.scoundrel.state

import com.copetti.dailyshuffle.scoundrel.Card

data class ScoundrelRoom(
    val sectors: List<ScoundrelRoomSector>
) {
    fun size() = sectors.size

    fun clear(index: Int): ScoundrelRoom {
        val newSectors = sectors.toMutableList()
        newSectors[index] = newSectors[index].empty()
        return ScoundrelRoom(newSectors)
    }

    fun getCards() = sectors.mapNotNull(ScoundrelRoomSector::card)


    companion object {

        fun buildScoundrelRoom(cards: List<Card?>) = ScoundrelRoom(
            sectors = cards.mapIndexed { index, card -> ScoundrelRoomSector(index, card) }
        )
    }
}
