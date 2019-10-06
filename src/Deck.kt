import java.util.*
import kotlin.collections.ArrayList

class Deck {
    private lateinit var cards: ArrayList<Card>

    init {
        resetDeck()
    }

    private fun resetDeck() {
        cards = ArrayList()
        for (suit in 0..3) {
            for (rank in 0..12) {
                cards.add(Card(rank, suit))
            }
        }

        var indexOne: Int
        var indexTwo: Int
        val generator = Random()
        var temp: Card
        for (i in 0..200) {
            indexOne = generator.nextInt(cards.size - 1)
            indexTwo = generator.nextInt(cards.size - 1)

            temp = cards[indexTwo]
            cards[indexTwo] = cards[indexOne]
            cards[indexOne] = temp
        }
    }

    fun drawFromDeck(): Card {
        if (cards.size == 1) {
            resetDeck()
        }
        return cards.removeAt(0)
    }
}