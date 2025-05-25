package com.copetti.dailyshuffle.scoundrel.command.factories

import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommand
import com.copetti.dailyshuffle.scoundrel.com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommandFactory
import com.copetti.dailyshuffle.scoundrel.command.commands.DrinkPotionCommand

class DrinkPotionCommandFactory: ScoundrelCommandFactory {
    override fun createCommands(state: ScoundrelGameState): List<ScoundrelCommand> {
        return listOf(DrinkPotionCommand())
    }
} 