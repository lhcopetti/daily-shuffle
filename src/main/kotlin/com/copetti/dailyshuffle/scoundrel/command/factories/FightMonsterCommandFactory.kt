package com.copetti.dailyshuffle.scoundrel.command.factories

import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommand
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommandFactory
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelTarget
import com.copetti.dailyshuffle.scoundrel.command.commands.FightMonsterCommand
import com.copetti.dailyshuffle.scoundrel.engine.ScoundrelMonster
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState

class FightMonsterCommandFactory : ScoundrelCommandFactory {
    override fun createCommands(state: ScoundrelGameState): List<ScoundrelCommand> {

        val commands = mutableListOf<ScoundrelCommand>()

        for (sector in state.room.sectors) {
            val card = sector.toScoundrelCard()
            if (card !is ScoundrelMonster)
                continue

            commands.add(FightMonsterCommand(ScoundrelTarget(index = sector.roomIndex, value = card)))
        }

        return commands
    }
} 