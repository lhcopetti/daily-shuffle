import com.copetti.dailyshuffle.scoundrel.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.random.Random

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

    @Test
    fun `newShuffledDeck with different seeds produces different card orders`() {
        val deck1 = CardDeck.newShuffledDeck(Random(1))
        val deck2 = CardDeck.newShuffledDeck(Random(2))
        assertFalse(deck1.cards == deck2.cards, "Decks with different seeds should have different card orders")
        assertEquals(
            deck1.cards.toSet(),
            deck2.cards.toSet(),
            "Decks should contain the same cards, just in different order"
        )
    }

    @Test
    fun `newShuffledDeck with same seed produces same card order`() {
        val deck1 = CardDeck.newShuffledDeck(Random(42))
        val deck2 = CardDeck.newShuffledDeck(Random(42))
        assertEquals(deck1.cards, deck2.cards, "Decks with same seed should have identical card order")
    }

    @Test
    fun `filtered with multiple conditions returns correct cards`() {
        val deck = CardDeck.newShuffledDeck(Random(42))
        val filtered = deck.filtered(object : DeckFilteringStrategy {
            override fun filter(card: Card) = card.suit == CardSuit.HEARTS
        })

        assertEquals(13, filtered.cards.size, "Should only contain hearts")
        assertTrue(filtered.cards.all { it.suit == CardSuit.HEARTS })
    }

    @Test
    fun `filtered with no matching cards returns empty deck`() {
        val deck = CardDeck.newShuffledDeck(Random(42))
        val filtered = deck.filtered(object : DeckFilteringStrategy {
            override fun filter(card: Card) = false
        })

        assertTrue(filtered.cards.isEmpty(), "Should return empty deck when no cards match filter")
    }

    @Test
    fun `bottomRandomly preserves original deck order`() {
        val originalCards = listOf(
            Card(CardRank.ACE, CardSuit.CLUBS),
            Card(CardRank.TWO, CardSuit.CLUBS)
        )
        val deck = CardDeck(originalCards)
        val toAdd = listOf(Card(CardRank.THREE, CardSuit.HEARTS))

        val newDeck = deck.bottomRandomly(toAdd, Random(42))

        // Check that original cards maintain their relative order
        val originalCardsInNewDeck = newDeck.cards.filter { it in originalCards }
        assertEquals(originalCards, originalCardsInNewDeck, "Original cards should maintain their order")
    }

    @Test
    fun `bottomRandomly with empty list returns original deck`() {
        val originalDeck = CardDeck.newShuffledDeck(Random(42))
        val newDeck = originalDeck.bottomRandomly(emptyList(), Random(42))

        assertEquals(originalDeck.cards, newDeck.cards, "Adding empty list should return identical deck")
    }

    @Test
    fun `drawAtMost with zero count returns empty list and original deck`() {
        val originalDeck = CardDeck.newShuffledDeck(Random(42))
        val (remaining, drawn) = originalDeck.drawAtMost(0)

        assertTrue(drawn.isEmpty(), "Should draw no cards")
        assertEquals(originalDeck.cards, remaining.cards, "Remaining deck should be unchanged")
    }

    @Test
    fun `drawAtMost with negative count throws IllegalArgumentException`() {
        val deck = CardDeck.newShuffledDeck(Random(42))
        assertThrows<IllegalArgumentException> {
            deck.drawAtMost(-1)
        }
    }
} 