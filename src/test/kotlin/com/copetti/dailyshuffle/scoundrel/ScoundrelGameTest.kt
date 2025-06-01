package com.copetti.dailyshuffle.scoundrel

import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameStatus
import org.junit.jupiter.api.Assertions.assertTimeoutPreemptively
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import java.time.Duration
import java.util.*
import java.util.concurrent.CountDownLatch
import kotlin.random.Random

class ScoundrelGameTest {

    @Test
    fun `should have a successful game within a limited number of rounds`() {
        val latch = CountDownLatch(1)

        val seeds = setOf(
            8039375570471121430,
            4253316924853939507,
            4242952504862482490,
            7564303770286631550,
            7747944934293600238,
            -8856920572300579013,
            7024063750045430864
        )
        assertTimeoutPreemptively(Duration.ofSeconds(300)) {

            seeds.forEach { seed ->
                Thread.startVirtualThread {
                    val games = PriorityQueue(Comparator.comparing(ScoundrelGame::gameRound).reversed())
                    games.add(createNewGame(seed = seed))

                    while (latch.count != 0L && games.isNotEmpty()) {
                        val currentGame = games.remove()

                        when (currentGame.gameStatus()) {
                            ScoundrelGameStatus.IN_PROGRESS -> {
                                val availableCommands = currentGame.getAvailableCommands()
                                availableCommands.forEach { command ->
                                    val newGame = currentGame.executeCommand(command)
                                    games.add(newGame)
                                }
                            }

                            ScoundrelGameStatus.VICTORY -> {
                                latch.countDown()
                                break
                            }

                            ScoundrelGameStatus.DEFEAT -> {}
                        }
                    }
                }
            }

            latch.await()
        }
    }

    @RepeatedTest(10)
    fun `verify games run to completion with random commands`() {
        assertTimeoutPreemptively(Duration.ofSeconds(30)) {

            var game = createNewGame()

            while (game.gameStatus() == ScoundrelGameStatus.IN_PROGRESS) {
                val commands = game.getAvailableCommands()
                assertTrue(
                    commands.isNotEmpty(),
                    "Game has no available commands but is still in progress"
                )

                val randomCommand = commands[Random.nextInt(commands.size)]
                game = game.executeCommand(randomCommand)
            }

            assertTrue(game.gameStatus() == ScoundrelGameStatus.VICTORY || game.gameStatus() == ScoundrelGameStatus.DEFEAT)

        }
    }


    private fun createNewGame(seed: Long = Random.nextLong(), startingLife: Int? = null): ScoundrelGame {
        val random = Random(seed)
        val initialDeck = CardDeck.newShuffledDeck(random)
        return ScoundrelGame.newGame(random, initialDeck, startingLife)
    }

}