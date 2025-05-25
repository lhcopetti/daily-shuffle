package com.copetti.dailyshuffle.scoundrel

import com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameStatus
import com.copetti.dailyshuffle.scoundrel.state.ScoundrelRoom

interface ScoundrelGameView {

    fun gameStatus(): ScoundrelGameStatus

    fun dungeonSize(): Int

    fun room(): ScoundrelRoom

    fun life(): Int
}