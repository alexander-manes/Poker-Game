import HandUtil.Companion.sortByRank

/**
 * HandValue class is a representation of the running value of the hand to be used in comparining to other hands
 * type is type of hand: 0 is high card, 1 is pair, and so on with the best value being a royal flush as a 9
 */
class HandValue {
    var cards = ArrayList<Card>()
    lateinit var highestCard: Card
    var handType = -1

    fun setValue(handCards: ArrayList<Card>) {
        cards.addAll(handCards)
        sortByRank(cards)
        highestCard = if (cards[0].rank == 0) {
            Card(0, cards[0].suit)
        } else {
            Card(cards[4].rank, cards[4].suit)
        }

        if (cards.size != 5) {
            return
        }
        handType = when {
            isRoyalFlush() -> 9
            isStraight() -> 8
            is4s() -> 7
            isFullHouse() -> 6
            isFlush() -> 5
            isStraight() -> 4
            is3s() -> 3
            is22s() -> 2
            is2s() -> 1
            else -> 0
        }
    }

    private fun isRoyalFlush(): Boolean {
        return isStraightFlush() && highestCard.rank == 0
    }

    private fun isStraightFlush(): Boolean {
        return isStraight() && isFlush()
    }

    private fun isStraight(): Boolean {
        sortByRank(cards)
        if (cards[0].rank == 0) {
            val a = cards[1].rank == 1 && cards[2].rank == 2 && cards[3].rank == 3 && cards[4].rank == 4
            val b = cards[1].rank == 9 && cards[2].rank == 10 && cards[3].rank == 11 && cards[4].rank == 12
            return a || b
        } else {
            var testRank = cards[0].rank + 1
            for (i in 1 until cards.size) {
                if (cards[i].rank != testRank) {
                    return false
                }
                testRank++
            }
            return true
        }
    }

    private fun isFlush(): Boolean {
        HandUtil.sortBySuite(cards)
        return cards[0].suit == cards[cards.size - 1].suit
    }

    private fun is4s(): Boolean {
        sortByRank(cards)

        //check x x x x y
        val a = cards[0].rank == cards[1].rank && cards[1].rank == cards[2].rank
                && cards[2].rank == cards[3].rank && cards[3].rank == cards[4].rank

        //check y x x x x
        val b = cards[1].rank == cards[2].rank && cards[2].rank == cards[3].rank
                && cards[3].rank == cards[4].rank && cards[4].rank == cards[5].rank
        return a || b
    }

    private fun isFullHouse(): Boolean {
        sortByRank(cards)

        //check x x x y y
        val a = cards[0].rank == cards[1].rank && cards[1].rank == cards[2].rank
                && cards[3].rank == cards[4].rank

        //check x x y y y
        val b = cards[0].rank == cards[1].rank
                && cards[2].rank == cards[3].rank && cards[3].rank == cards[4].rank

        return a || b
    }

    private fun is3s(): Boolean {
        if (is4s() || isFullHouse()) {
            return false
        }

        sortByRank(cards)

        val a = cards[0].rank == cards[1].rank && cards[1].rank == cards[2].rank
        val b = cards[1].rank == cards[2].rank && cards[2].rank == cards[3].rank
        val c = cards[2].rank == cards[3].rank && cards[3].rank == cards[4].rank

        return a || b || c
    }

    private fun is22s(): Boolean {
        if (is4s() || is3s() || isFullHouse()) {
            return false
        }
        sortByRank(cards)

        val a = cards[0].rank == cards[1].rank && cards[2].rank == cards[3].rank
        val b = cards[0].rank == cards[1].rank && cards[3].rank == cards[4].rank
        val c = cards[1].rank == cards[2].rank && cards[3].rank == cards[4].rank

        return a || b || c
    }

    private fun is2s(): Boolean {
        if (is4s() || is3s() || isFullHouse() || is22s()) {
            return false
        }
        sortByRank(cards)

        val a = cards[0].rank == cards[1].rank
        val b = cards[1].rank == cards[2].rank
        val c = cards[2].rank == cards[3].rank
        val d = cards[3].rank == cards[4].rank

        return a || b || c || d
    }
}