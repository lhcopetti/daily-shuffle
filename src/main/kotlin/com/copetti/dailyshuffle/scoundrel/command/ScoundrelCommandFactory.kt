package com.copetti.dailyshuffle.scoundrel.command

import com.copetti.dailyshuffle.scoundrel.com.copetti.dailyshuffle.scoundrel.state.ScoundrelGameState

interface ScoundrelCommandFactory  {

    fun createCommands(state: ScoundrelGameState): List<ScoundrelCommand>
}