package com.copetti.dailyshuffle.scoundrel

class ScoundrelGame private constructor(
    private val deck: CardDeck,
    private val room: List<Card>,
    val life: Int
) {


    fun getRoom(): List<Card> {
        return room
    }

    fun executeCommand(command: ScoundrelGameCommand) {

    }

    companion object {

        private const val ROOM_SIZE: Int = 4
        private const val STARTING_LIFE: Int = 20

        fun newGame(deck: CardDeck): ScoundrelGame {
            val scoundrelDeck = deck.filtered(ScoundrelDeckFilteringStrategy())

            if (scoundrelDeck.cards.isEmpty())
                throw ScoundrelException("Scoundrel game cannot be started without any playing cards")

            val (dungeon, room) = scoundrelDeck.drawAtMost(ROOM_SIZE)
            return ScoundrelGame(dungeon, room, STARTING_LIFE)
        }

    }
}

class ScoundrelDeckFilteringStrategy : DeckFilteringStrategy {
    override fun filter(card: Card): Boolean {
        if (card.suit.color == CardColor.RED && card.rank.isFaceCard())
            return false

        if (card.suit.color == CardColor.RED && card.rank == CardRank.ACE)
            return false

        return true
    }

}