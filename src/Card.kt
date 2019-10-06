data class Card(val rank: Int, val suit: Int) {


    companion object {
        val suits = arrayOf("hearts", "spades", "diamonds", "clubs")
        val ranks = arrayOf("Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King")
        fun rankToString(rank: Int): String {
            return ranks[rank]
        }

        fun suitToString(suit: Int): String {
            return suits[suit]
        }
    }

    override fun toString(): String {
        return rankToString(rank) + " of " + suitToString(suit)
    }
}