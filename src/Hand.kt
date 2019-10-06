import kotlin.collections.ArrayList

class Hand : Comparable<Hand> {
    var cards = ArrayList<Card>()
    val value = HandValue()

    override fun compareTo(other: Hand): Int {
        if (value.handType > other.value.handType) {
            return 1
        } else if (value.handType < other.value.handType) {
            return -1
        }
        return when {
            value.highestCard.rank > other.value.highestCard.rank -> 1
            value.highestCard.rank < other.value.highestCard.rank -> -1
            else -> 0
        }
    }

    fun calcValue() {
        if (cards.size == 5) {
            value.setValue(cards)
        }
    }

    override fun toString(): String {
        var str = ""
        for (card in cards) {
            str += "\n$card"
        }
        if (value.handType != -1) {
            val type = handTypes[value.handType]
            str += "\nWhich means you have a $type"
        }
        return str
    }

    companion object {
        val handTypes = arrayOf(
            "High Card", "Pair", "Two Pair", "Three of a Kind",
            "Straight", "Flush", "Full House", "Four of a Kind", "Straight Flush", "Royal Flush"
        )
    }
}