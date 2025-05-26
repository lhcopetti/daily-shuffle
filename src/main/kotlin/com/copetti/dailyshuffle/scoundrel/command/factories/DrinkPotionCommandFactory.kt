package com.copetti.dailyshuffle.scoundrel.command.factories

import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommand
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommandFactory
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelTarget
import com.copetti.dailyshuffle.scoundrel.command.commands.DiscardHealthPotionCommand
import com.copetti.dailyshuffle.scoundrel.command.commands.DrinkPotionCommand
import com.copetti.dailyshuffle.scoundrel.engine.ScoundrelPotion
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import kotlin.math.min

class DrinkPotionCommandFactory : ScoundrelCommandFactory {
    override fun createCommands(state: ScoundrelGameState): List<ScoundrelCommand> {

        val commands = mutableListOf<ScoundrelCommand>()
        for (sector in state.room.sectors) {
            val card = sector.toScoundrelCard()

            if (card !is ScoundrelPotion)
                continue

            val target = ScoundrelTarget(sector.roomIndex, card)
            if (state.drankPotionInRoom)
                commands.add(DiscardHealthPotionCommand(target))
            else {
                val maxHealingPossible = ScoundrelGameState.STARTING_LIFE - state.life
                val lifeBonus = min(card.lifeBonus, maxHealingPossible)
                commands.add(DrinkPotionCommand(lifeBonus, target))
            }
        }

        return commands
    }
}