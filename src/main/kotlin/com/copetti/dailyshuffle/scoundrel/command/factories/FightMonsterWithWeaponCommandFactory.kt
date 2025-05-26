package com.copetti.dailyshuffle.scoundrel.command.factories

import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommand
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommandFactory
import com.copetti.dailyshuffle.scoundrel.command.commands.FightMonsterWithWeaponCommand
import com.copetti.dailyshuffle.scoundrel.engine.ScoundrelCardMapper
import com.copetti.dailyshuffle.scoundrel.engine.ScoundrelType
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState
import kotlin.math.max

class FightMonsterWithWeaponCommandFactory : ScoundrelCommandFactory {
    override fun createCommands(state: ScoundrelGameState): List<ScoundrelCommand> {
        if (state.equippedWeapon == null)
            return listOf()

        val commands = mutableListOf<ScoundrelCommand>()
        for (sector in state.room.sectors) {
            val card = sector.card ?: continue
            val monsterCard = ScoundrelCardMapper.toScoundrelCard(card)

            if (monsterCard.type != ScoundrelType.MONSTER) continue

            val durability = state.equippedWeapon.durability()
            if (durability != null && durability <= monsterCard.value)
                continue;

            commands.add(
                FightMonsterWithWeaponCommand(
                    target = sector.roomIndex,
                    monster = card,
                    damage = max(0, monsterCard.value - state.equippedWeapon.attack)
                )
            )
        }

        return commands
    }

}