package com.copetti.dailyshuffle.scoundrel.command.factories

import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommand
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommandFactory
import com.copetti.dailyshuffle.scoundrel.command.commands.SkipRoomCommand

class SkipRoomCommandFactory: ScoundrelCommandFactory {

    override fun createCommands(state: ScoundrelGameState): List<ScoundrelCommand> {
        if (state.skippedLastRoom)
            return listOf()

        return listOf(SkipRoomCommand())
    }

}