package com.copetti.dailyshuffle.scoundrel.command.commands

import com.copetti.dailyshuffle.scoundrel.com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommand
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelRoom

class SkipRoomCommand : ScoundrelCommand {
    override fun execute(state: ScoundrelGameState): ScoundrelGameState {
        val (deck, newRoom) = state.deck.drawAtMost(state.room.size())
        val roomCards = state.room.cards.filterNotNull()
        return state.copy(
            deck = deck.bottomRandomly(roomCards, state.random),
            room = ScoundrelRoom(newRoom),
            skippedLastRoom = true
        )
    }

    override fun displayName(): String = "Skip Room"
}