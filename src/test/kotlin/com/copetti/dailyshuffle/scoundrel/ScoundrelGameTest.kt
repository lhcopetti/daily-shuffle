package com.copetti.dailyshuffle.scoundrel

import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameStatus
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.assertTimeout
import java.time.Duration
import kotlin.random.Random

class ScoundrelGameTest {

    @RepeatedTest(100)
    fun `verify run games to completion with random commands`() {
        assertTimeout(Duration.ofSeconds(30)) {
            val victories = mutableListOf<ScoundrelGame>()
            val defeats = mutableListOf<ScoundrelGame>()

            repeat(100) { gameNumber ->
                val seed = Random.nextLong()
                val random = Random(seed)
                var game = createNewGame(random)

                while (game.gameStatus() == ScoundrelGameStatus.IN_PROGRESS) {
                    val commands = game.getAvailableCommands()
                    assertTrue(
                        commands.isNotEmpty(),
                        "Game $gameNumber (seed: $seed) has no available commands but is still in progress"
                    )

                    val randomCommand = commands[random.nextInt(commands.size)]
                    game = game.executeCommand(randomCommand)
                }

                when (game.gameStatus()) {
                    ScoundrelGameStatus.VICTORY -> victories.add(game)
                    ScoundrelGameStatus.DEFEAT -> defeats.add(game)
                    else -> fail("Game ended in unexpected status: ${game.gameStatus()}")
                }
            }

            println("Games completed:")
            println("Victories: ${victories.size}")
            println("Defeats: ${defeats.size}")

            // Ensure all games ended
            assertEquals(100, victories.size + defeats.size, "Not all games completed")
        }
    }


    private fun createNewGame(
        random: Random = Random,
        startingLife: Int? = null
    ): ScoundrelGame {
        val initialDeck = CardDeck.newShuffledDeck(random)
        return ScoundrelGame.newGame(random, initialDeck, startingLife)
    }

}