package com.copetti.dailyshuffle.scoundrel.state

import com.copetti.dailyshuffle.scoundrel.Card

data class ScoundrelRoom(
    val cards: List<Card?>
) {
    fun size() = cards.size


    fun clear(index: Int): ScoundrelRoom {
        val newRoomCards = cards.toMutableList()
        newRoomCards[index] = null
        return ScoundrelRoom(newRoomCards)
    }

}
