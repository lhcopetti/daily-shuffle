package com.copetti.dailyshuffle.scoundrel

enum class CardRank(
    val value: String
) {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");

    fun isFaceCard() = this in listOf(JACK, QUEEN, KING)
}

enum class CardSuit(
    val value: String,
    val color: CardColor
) {
    CLUBS("C", CardColor.BLACK),
    DIAMONDS("D", CardColor.RED),
    HEARTS("H", CardColor.RED),
    SPADES("S", CardColor.BLACK);
}

enum class CardColor {
    BLACK,
    RED
}

class Card(
    val rank: CardRank,
    val suit: CardSuit
) {
    fun toShortFormat() = rank.value + suit.value
}