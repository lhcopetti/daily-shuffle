package com.copetti.dailyshuffle.scoundrel.command

import com.copetti.dailyshuffle.scoundrel.com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState

interface ScoundrelCommand {

    fun execute(state: ScoundrelGameState): ScoundrelGameState
    fun displayName(): String
}
