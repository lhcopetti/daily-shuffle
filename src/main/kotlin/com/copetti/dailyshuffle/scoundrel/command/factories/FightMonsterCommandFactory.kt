package com.copetti.dailyshuffle.scoundrel.command.factories

import com.copetti.dailyshuffle.scoundrel.com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommand
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommandFactory
import com.copetti.dailyshuffle.scoundrel.command.commands.FightMonsterCommand
import com.copetti.dailyshuffle.scoundrel.engine.ScoundrelType

class FightMonsterCommandFactory : ScoundrelCommandFactory {
    override fun createCommands(state: ScoundrelGameState): List<ScoundrelCommand> {
        return state.room.sectors
            .filter { sector -> sector.toScoundrelCard()?.type == ScoundrelType.MONSTER }
            .map { sector ->
                FightMonsterCommand(damage = sector.toScoundrelCard()!!.value, target = sector.roomIndex)
            }
    }
} 