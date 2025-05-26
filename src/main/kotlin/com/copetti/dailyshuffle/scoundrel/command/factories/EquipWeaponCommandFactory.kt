package com.copetti.dailyshuffle.scoundrel.command.factories

import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommand
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommandFactory
import com.copetti.dailyshuffle.scoundrel.command.commands.EquipWeaponCommand
import com.copetti.dailyshuffle.scoundrel.engine.ScoundrelType

class EquipWeaponCommandFactory : ScoundrelCommandFactory {
    override fun createCommands(state: ScoundrelGameState): List<ScoundrelCommand> {
        return state.room.sectors
            .filter { sector -> sector.toScoundrelCard()?.type == ScoundrelType.WEAPON }
            .map { sector ->
                EquipWeaponCommand(
                    attackBonus = sector.toScoundrelCard()!!.value,
                    target = sector.roomIndex
                )
            }
    }
} 