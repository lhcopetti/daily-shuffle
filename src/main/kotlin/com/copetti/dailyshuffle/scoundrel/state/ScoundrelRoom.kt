package com.copetti.dailyshuffle.scoundrel.state

import com.copetti.dailyshuffle.scoundrel.Card

data class ScoundrelRoom(
    val sectors: List<ScoundrelRoomSector>
) {
    fun size() = sectors.size

    fun empty() = ScoundrelRoom(
        sectors.map(ScoundrelRoomSector::empty)
    )

    fun clear(index: Int): ScoundrelRoom {
        val newSectors = sectors.toMutableList()
        newSectors[index] = newSectors[index].empty()
        return ScoundrelRoom(newSectors)
    }

    fun load(newCards: List<Card>): ScoundrelRoom {
        val roomCards = mutableListOf<Card?>()
        roomCards.addAll(getCards())
        roomCards.addAll(newCards)

        while (sectors.size > roomCards.size) {
            roomCards.add(null)
        }

        if (roomCards.size > sectors.size) {
            throw IllegalArgumentException("There are more cards than sectors available in the room")
        }

        return buildScoundrelRoom(roomCards)
    }

    fun getCards() = sectors.mapNotNull(ScoundrelRoomSector::card)


    companion object {

        fun buildScoundrelRoom(cards: List<Card?>) = ScoundrelRoom(
            sectors = cards.mapIndexed { index, card -> ScoundrelRoomSector(index, card) }
        )
    }
}
