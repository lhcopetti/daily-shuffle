package com.copetti.dailyshuffle.scoundrel.engine

import com.copetti.dailyshuffle.scoundrel.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ScoundrelCardMapperTest {
    @Test
    fun `maps CLUBS to ENEMY and correct value`() {
        val card = Card(CardRank.FIVE, CardSuit.CLUBS)
        val scoundrelCard = ScoundrelCardMapper.toScoundrelCard(card)
        assertEquals(ScoundrelType.ENEMY, scoundrelCard.type)
        assertEquals(5, scoundrelCard.value)
    }

    @Test
    fun `maps SPADES to ENEMY and correct value`() {
        val card = Card(CardRank.KING, CardSuit.SPADES)
        val scoundrelCard = ScoundrelCardMapper.toScoundrelCard(card)
        assertEquals(ScoundrelType.ENEMY, scoundrelCard.type)
        assertEquals(13, scoundrelCard.value)
    }

    @Test
    fun `maps HEARTS to POTION and ACE to 14`() {
        val card = Card(CardRank.ACE, CardSuit.HEARTS)
        val scoundrelCard = ScoundrelCardMapper.toScoundrelCard(card)
        assertEquals(ScoundrelType.POTION, scoundrelCard.type)
        assertEquals(14, scoundrelCard.value)
    }

    @Test
    fun `maps DIAMONDS to EQUIPMENT and correct value`() {
        val card = Card(CardRank.TEN, CardSuit.DIAMONDS)
        val scoundrelCard = ScoundrelCardMapper.toScoundrelCard(card)
        assertEquals(ScoundrelType.EQUIPMENT, scoundrelCard.type)
        assertEquals(10, scoundrelCard.value)
    }
} 