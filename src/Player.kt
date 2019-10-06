class Player(startingAmount: Int, val name: String) {
    var hand = Hand()
    var amount = startingAmount

    fun dealCard(card: Card) {
        hand.cards.add(card)
        if (hand.cards.size == 5) {
            hand.calcValue()
        }
    }

    override fun toString(): String {
        return "You have $amount and a hand of: $hand"
    }


}