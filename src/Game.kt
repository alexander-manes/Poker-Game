import java.util.Scanner

val scanner = Scanner(System.`in`)
val players = ArrayList<Player>()
val deck = Deck()
var pot = 0
var foldedPlayers = HashSet<Player>()


fun main() {
    var playing = true
    println("Welcome to Poker")
    println("How many people are playing?")

    val numbPlayers = getIntInput(2, "you need at least two players")
    for (i in 0 until numbPlayers) {
        println("Enter player ${i + 1}'s name")
        val name = getStringInput(null)
        players.add(Player(100, name))
    }

    while (playing) {
        foldedPlayers = HashSet()
        pot = 0
        for (i in 1..5) {
            println("It is round $i and the pot is $pot")
            dealCards()
            takeBets()
        }
        var bestPlayerHand = players[0].hand
        var bestPlayers = arrayListOf<Player>(players[0])
        for (player in players) {
            if (bestPlayerHand < player.hand) {
                bestPlayers = arrayListOf(player)
                bestPlayerHand = player.hand
            } else if (bestPlayerHand == player.hand) {
                bestPlayers.add(player)
            }
        }
        val win = pot / bestPlayers.size
        for (player in bestPlayers) {
            player.amount += pot / win
            println("${player.name} won $win! and now has ${player.amount}")
        }
        println("Play again? (Y/N)")
        if (getStringInput(arrayOf("Y", "N")) == "N") {
            playing = false
        }

    }
}

fun dealCards() {
    for (player in players) {
        player.dealCard(deck.drawFromDeck())
    }
}

fun takeBets() {
    for (player in players) {
        if (!foldedPlayers.contains(player)) {
            clearScreen()
            println("\n\nAll players look away from the screen")
            //println("${player.name}, are you ready to see your cards? (Y)")
            //getStringInput(arrayOf("Y"))
            println(player)
            println("The pot is $pot. ${player.name}, how much would you like to bet this round? (F to fold)")
            val bet = getIntInput(0, "you need to bet at least 0")
            if(bet != -1) {
                pot += bet
                player.amount -= bet
            } else {
                foldedPlayers.add(player)
            }
        }
    }
}

fun clearScreen() {
    for (i in 0..500){
        println()
    }
}

fun getIntInput(min: Int, errorText : String): Int {
    var input: String
    while (true) {
        input = scanner.next()
        try {
            val int = Integer.parseInt(input)
            if(int < min) {
                println("Invalid input $errorText try again or Quit (Q)")
            } else {
                return int
            }
        } catch (e: NumberFormatException) {
            if (input == "Q"){
                quitProgram()
            } else if (input == "F") {
                return -1
            }
            println("Invalid input $errorText try again or Quit (Q)")
        }
    }
}

fun getStringInput(acceptableAnswers: Array<String>?): String {
    var input: String
    while (true) {
        input = scanner.next()
        if (input == "Q") {
            quitProgram()
        }
        if (acceptableAnswers == null || input in acceptableAnswers) {
            return input
        } else {
            println("Invalid input try again or Quit (Q)")
        }
    }
}

fun quitProgram() {
    println("##QUITING##")
}