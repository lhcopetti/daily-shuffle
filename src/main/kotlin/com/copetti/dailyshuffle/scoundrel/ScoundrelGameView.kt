package com.copetti.dailyshuffle.scoundrel

interface ScoundrelGameView {

    fun dungeonSize(): Int

    fun room(): List<Card>

    fun life(): Int
}