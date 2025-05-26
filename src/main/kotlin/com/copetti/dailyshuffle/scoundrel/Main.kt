package com.copetti.dailyshuffle.scoundrel

import kotlin.random.Random

fun main() {
    println("******************************")
    println("******* Scoundrel game *******")
    println("******************************")

    val seed = 33L
    val random = Random(seed)
    val initialDeck = CardDeck.newShuffledDeck(random)
    val game = ScoundrelGame.newGame(random, initialDeck)

    var currentGame = game
    while(true) {
        printGame(currentGame)
        val status = currentGame.gameStatus()
        if (status != com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameStatus.IN_PROGRESS) {
            when (status) {
                com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameStatus.VICTORY -> {
                    println("\n==============================")
                    println("🎉 CONGRATULATIONS! 🎉")
                    println("You have conquered the dungeon and emerged victorious!")
                    println("==============================\n")
                }
                com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameStatus.DEFEAT -> {
                    println("\n==============================")
                    println("💀 GAME OVER 💀")
                    println("You have fallen in the dungeon. Better luck next time!")
                    println("==============================\n")
                }
                else -> {}
            }
            break
        }
        val commands = currentGame.getAvailableCommands()
        println("\nOptions:")
        println("0: Exit game")
        commands.forEachIndexed { idx, cmd ->
            println("${idx + 1}: ${cmd.displayName()}")
        }
        print("Enter your choice: ")
        val input = readlnOrNull()?.trim()
        val choice = input?.toIntOrNull()
        if (choice == null || choice < 0 || choice > commands.size) {
            println("Invalid option. Please choose again.")
            continue
        }
        if (choice == 0) {
            println("Exiting the game. Goodbye!")
            break
        }
        val selectedCommand = commands[choice - 1]
        println("Executing: ${selectedCommand.displayName()}")
        currentGame = currentGame.executeCommand(selectedCommand)
    }
}

private fun printGame(game: ScoundrelGame) {
    println()
    println("------------------------------")
    println("Scoundrel Game - Current State")
    println("Your current life is: ${game.life()}")
    println("Cards remaining in dungeon: ${game.dungeonSize()}")
    
    println(getWeaponInfo(game))

    println()
    print("The room is: [${game.room().sectors.joinToString(separator = " - ") { it.card?.toShortFormat() ?: "empty" }}]")
    println()
}

private fun getWeaponInfo(game: ScoundrelGame): String {
    val weaponInfo = StringBuilder("Weapon Bonus: ")
    val weapon = game.equippedWeapon() ?: return weaponInfo.append("No weapon equipped").toString()

    weaponInfo.append(" +${weapon.attack} attack")
    if (weapon.durability() != null)
        weaponInfo.append(" ${weapon.durability()} durability")

    return weaponInfo.toString()
}