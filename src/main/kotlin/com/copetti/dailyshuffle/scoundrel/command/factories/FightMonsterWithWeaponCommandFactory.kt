package com.copetti.dailyshuffle.scoundrel.command.factories

import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommand
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelCommandFactory
import com.copetti.dailyshuffle.scoundrel.command.ScoundrelTarget
import com.copetti.dailyshuffle.scoundrel.command.commands.FightMonsterWithWeaponCommand
import com.copetti.dailyshuffle.scoundrel.engine.ScoundrelCardMapper
import com.copetti.dailyshuffle.scoundrel.engine.ScoundrelMonster
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

            if (monsterCard !is ScoundrelMonster) continue

            val durability = state.equippedWeapon.durability()
            if (durability != null && durability <= monsterCard.attackPower)
                continue;

            val target = ScoundrelTarget(index = sector.roomIndex, value = monsterCard)
            val command = FightMonsterWithWeaponCommand(
                target = target,
                damage = max(0, monsterCard.attackPower - state.equippedWeapon.attack)
            )

            commands.add(command)
        }

        return commands
    }

}