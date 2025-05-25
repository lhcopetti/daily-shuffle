package com.copetti.dailyshuffle.scoundrel.command.factories

import com.copetti.dailyshuffle.scoundrel.com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommand
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommandFactory
import com.copetti.dailyshuffle.scoundrel.command.commands.DiscardHealthPotionCommand
import com.copetti.dailyshuffle.scoundrel.command.commands.DrinkPotionCommand
import com.copetti.dailyshuffle.scoundrel.engine.ScoundrelType

class DrinkPotionCommandFactory : ScoundrelCommandFactory {
    override fun createCommands(state: ScoundrelGameState): List<ScoundrelCommand> {
        return state.room.sectors
            .filter { sector -> sector.toScoundrelCard()?.type == ScoundrelType.POTION }
            .map { sector ->
                if (state.drankPotionInRoom)
                    DiscardHealthPotionCommand(sector.roomIndex)
                else
                    DrinkPotionCommand(sector.toScoundrelCard()!!.value, sector.roomIndex)
            }
    }
}