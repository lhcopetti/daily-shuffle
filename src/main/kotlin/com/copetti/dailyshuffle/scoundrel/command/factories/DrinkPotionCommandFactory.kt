package com.copetti.dailyshuffle.scoundrel.command.factories

import com.copetti.dailyshuffle.scoundrel.com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommand
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommandFactory
import com.copetti.dailyshuffle.scoundrel.command.commands.DiscardHealthPotionCommand
import com.copetti.dailyshuffle.scoundrel.command.commands.DrinkPotionCommand
import com.copetti.dailyshuffle.scoundrel.engine.ScoundrelCardMapper
import com.copetti.dailyshuffle.scoundrel.engine.ScoundrelType

class DrinkPotionCommandFactory : ScoundrelCommandFactory {
    override fun createCommands(state: ScoundrelGameState): List<ScoundrelCommand> {
        val commands = mutableListOf<ScoundrelCommand>()
        state.room.cards.forEachIndexed { index, card ->
            val scoundrelCard = card?.let { ScoundrelCardMapper.toScoundrelCard(it) }
            if (scoundrelCard != null && scoundrelCard.type == ScoundrelType.POTION) {
                if (state.drankPotionInRoom) {
                    commands.add(DiscardHealthPotionCommand(target = index))
                } else {
                    commands.add(DrinkPotionCommand(lifeBonus = scoundrelCard.value, target = index))
                }
            }
        }
        return commands
    }
}