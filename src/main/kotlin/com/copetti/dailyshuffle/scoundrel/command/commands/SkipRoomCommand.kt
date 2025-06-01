package com.copetti.dailyshuffle.scoundrel.command.commands

import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommand
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelRoom

class SkipRoomCommand : ScoundrelCommand {
    override fun execute(state: ScoundrelGameState): ScoundrelGameState {
        val (deck, drawnCards) = state.deck.drawAtMost(state.room.size())
        val roomCards = state.room.getCards()
        return state.copy(
            deck = deck.bottomRandomly(roomCards, state.random),
            room = state.room.empty().load(drawnCards),
            skippedLastRoom = true
        )
    }

    override fun displayName(): String = "Skip Room"
}