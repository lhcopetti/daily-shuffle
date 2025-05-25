package com.copetti.dailyshuffle.scoundrel.state

import com.copetti.dailyshuffle.scoundrel.Card

data class ScoundrelRoom(
    val cards: List<Card?>
) {
    fun size() = cards.size
}
