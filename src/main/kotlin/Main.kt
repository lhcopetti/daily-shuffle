package com.copetti.dailyshuffle.scoundrel

fun main() {
    println("******************************")
    println("******* Scoundrel game *******")
    println("******************************")

    val seed = 37L
    val initialDeck = CardDeck.newShuffledDeck(seed)
    val game = ScoundrelGame.newGame(initialDeck)

    val room = game.getRoom()
    val lifeTotal = game.life


    println()
    println("------------------------------")
    println("Scoundrel Game - Current State")
    println("Your current life is: $lifeTotal")
    printRoom(room)
    println()
}

private fun printRoom(room: List<Card>) {

    print("The room is: [${room.joinToString(separator = " - ") { it.toShortFormat() }}]")
}