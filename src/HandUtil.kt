class HandUtil {
    companion object {
        fun sortBySuite(cards : ArrayList<Card>){
            cards.clone()
            var minJ : Int

            for (i in 0 until cards.size) {
                minJ = i
                for (j in i + 1 until cards.size) {
                    if (cards[j].suit < cards[minJ].suit) {
                        minJ = j
                    }
                }

                val temp = cards[i]
                cards[i] = cards[minJ]
                cards[minJ] = temp
            }
        }

        fun sortByRank(cards: ArrayList<Card>) {
            cards.clone()
            var minJ: Int

            for (i in 0 until cards.size) {
                minJ = i
                for (j in i + 1 until cards.size) {
                    if (cards[j].rank < cards[minJ].rank) {
                        minJ = j
                    }
                }

                val temp = cards[i]
                cards[i] = cards[minJ]
                cards[minJ] = temp
            }
        }
    }
}