package com.copetti.dailyshuffle.scoundrel.engine

import com.copetti.dailyshuffle.scoundrel.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ScoundrelCardMapperTest {
    @Test
    fun `maps CLUBS to MONSTER and correct value`() {
        val card = Card(CardRank.FIVE, CardSuit.CLUBS)
        val scoundrelCard = ScoundrelCardMapper.toScoundrelCard(card)
        assertTrue(scoundrelCard is ScoundrelMonster)
        assertEquals(5, (scoundrelCard as ScoundrelMonster).attackPower)
    }

    @Test
    fun `maps SPADES to MONSTER and correct value`() {
        val card = Card(CardRank.KING, CardSuit.SPADES)
        val scoundrelCard = ScoundrelCardMapper.toScoundrelCard(card)
        assertTrue(scoundrelCard is ScoundrelMonster)
        assertEquals(13, (scoundrelCard as ScoundrelMonster).attackPower)
    }

    @Test
    fun `maps HEARTS to POTION and ACE to 14`() {
        val card = Card(CardRank.ACE, CardSuit.HEARTS)
        val scoundrelCard = ScoundrelCardMapper.toScoundrelCard(card)
        assertTrue(scoundrelCard is ScoundrelPotion)
        assertEquals(14, (scoundrelCard as ScoundrelPotion).lifeBonus)
    }

    @Test
    fun `maps DIAMONDS to WEAPON and correct value`() {
        val card = Card(CardRank.TEN, CardSuit.DIAMONDS)
        val scoundrelCard = ScoundrelCardMapper.toScoundrelCard(card)
        assertTrue(scoundrelCard is ScoundrelWeapon)
        assertEquals(10, (scoundrelCard as ScoundrelWeapon).attackBonus)
    }
} 