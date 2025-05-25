import com.copetti.dailyshuffle.scoundrel.*
import kotlin.random.Random
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `newShuffledDeck creates a deck with 52 unique cards`() {
        val deck = CardDeck.newShuffledDeck(Random(42))
        assertEquals(52, deck.cards.size)
        assertEquals(52, deck.cards.toSet().size) // all unique
    }

    @Test
    fun `drawAtMost returns correct number of cards and remaining deck`() {
        val deck = CardDeck.newShuffledDeck(Random(42))
        val (remaining, drawn) = deck.drawAtMost(5)
        assertEquals(5, drawn.size)
        assertEquals(47, remaining.cards.size)
    }

    @Test
    fun `filtered returns only cards matching the strategy`() {
        val deck = CardDeck(
            listOf(
                Card(CardRank.ACE, CardSuit.CLUBS),
                Card(CardRank.TWO, CardSuit.HEARTS)
            )
        )
        val filtered = deck.filtered(object : DeckFilteringStrategy {
            override fun filter(card: Card) = card.suit == CardSuit.CLUBS
        })
        assertEquals(1, filtered.cards.size)
        assertEquals(CardSuit.CLUBS, filtered.cards[0].suit)
    }

    @Test
    fun `bottomRandomly adds cards to the bottom in random order`() {
        val deck = CardDeck(listOf(Card(CardRank.ACE, CardSuit.CLUBS)))
        val toAdd = listOf(
            Card(CardRank.TWO, CardSuit.HEARTS),
            Card(CardRank.THREE, CardSuit.SPADES)
        )
        val random = Random(42)
        val newDeck = deck.bottomRandomly(toAdd, random)
        assertEquals(3, newDeck.cards.size)
        assertTrue(newDeck.cards.containsAll(deck.cards))
        assertTrue(newDeck.cards.containsAll(toAdd))
    }

    @Test
    fun `drawAtMost from empty deck returns empty deck and empty list`() {
        val deck = CardDeck(emptyList())
        val (remaining, drawn) = deck.drawAtMost(5)
        assertTrue(remaining.cards.isEmpty())
        assertTrue(drawn.isEmpty())
    }

    @Test
    fun `drawAtMost from deck with fewer cards than requested returns all cards`() {
        val cards = listOf(
            Card(CardRank.ACE, CardSuit.CLUBS),
            Card(CardRank.TWO, CardSuit.HEARTS)
        )
        val deck = CardDeck(cards)
        val (remaining, drawn) = deck.drawAtMost(5)
        assertTrue(remaining.cards.isEmpty())
        assertEquals(2, drawn.size)
        assertTrue(drawn.containsAll(cards))
    }
} 