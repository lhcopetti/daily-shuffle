package com.copetti.dailyshuffle.scoundrel.engine

import com.copetti.dailyshuffle.scoundrel.com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommand
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommandFactory
import com.copetti.dailyshuffle.scoundrel.command.factories.FightMonsterCommandFactory
import com.copetti.dailyshuffle.scoundrel.command.factories.DrinkPotionCommandFactory
import com.copetti.dailyshuffle.scoundrel.command.factories.SkipRoomCommandFactory

class ScoundrelGameEngine(
) {
    private val commandFactories: List<ScoundrelCommandFactory> = listOf(
        SkipRoomCommandFactory(),
        DrinkPotionCommandFactory(),
        FightMonsterCommandFactory()
    )

    fun getAvailableMoves(state: ScoundrelGameState): List<ScoundrelCommand> {
        return commandFactories.flatMap { f -> f.createCommands(state) }
    }
}