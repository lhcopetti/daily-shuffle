package com.copetti.dailyshuffle.scoundrel.command.commands

import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommand
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelRoom
import kotlin.random.Random

class SkipRoomCommand : ScoundrelCommand {
    override fun execute(state: ScoundrelGameState): ScoundrelGameState {
        val skipRoom = state.deck.bottomRandomly(state.room.getCards(), Random)
        val (deck, drawnCards) = skipRoom.drawAtMost(state.room.size())

        return state.copy(
            deck = deck,
            room = state.room.empty().load(drawnCards),
            skippedLastRoom = true
        )
    }

    override fun displayName(): String = "Skip Room"
}