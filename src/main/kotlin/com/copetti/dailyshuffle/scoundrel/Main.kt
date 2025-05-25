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
    print("The room is: [${game.room().sectors.joinToString(separator = " - ") { it.card?.toShortFormat() ?: "empty" }}]")
    println()
}