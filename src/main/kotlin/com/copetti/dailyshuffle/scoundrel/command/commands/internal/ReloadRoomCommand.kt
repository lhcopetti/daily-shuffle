package com.copetti.dailyshuffle.scoundrel.command.commands.internal

import com.copetti.dailyshuffle.scoundrel.Card
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommand
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelRoom

class ReloadRoomCommand : ScoundrelCommand {
    override fun execute(state: ScoundrelGameState): ScoundrelGameState {
        val cardsToDraw = state.room.sectors.size - state.room.getCards().size
        val (newDeck, drawnCards) = state.deck.drawAtMost(cardsToDraw)

        val roomCards = mutableListOf<Card?>()
        roomCards.addAll(state.room.getCards())
        roomCards.addAll(drawnCards)

        while (state.room.sectors.size > roomCards.size) {
            roomCards.add(null)
        }

        val newRoom = ScoundrelRoom.buildScoundrelRoom(roomCards)

        return state.copy(
            deck = newDeck,
            room = newRoom,
            drankPotionInRoom = false,
            skippedLastRoom = false
        )
    }

    override fun displayName() = "Reloads the room with extra cards"
}