package com.copetti.dailyshuffle.scoundrel.command.commands

import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommand
import com.copetti.dailyshuffle.scoundrel.com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState

class SkipRoomCommand : ScoundrelCommand {
    override fun execute(state: ScoundrelGameState): ScoundrelGameState {
        val (deck, newRoom) = state.deck.drawAtMost(state.room.size)
        return state.copy(
            deck = deck.bottomRandomly(state.room, state.random),
            room = newRoom,
            skippedLastRoom = true
        )
    }

    override fun displayName(): String = "Skip Room"
}