import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

internal class HandTest {

    @Test
    fun isRoyalFlush() {
        val royalFlush = Hand()
        royalFlush.cards = arrayListOf(
            Card(0, 0), Card(9, 0),
            Card(10, 0),
            Card(11, 0),
            Card(12, 0)
        )
        royalFlush.calcValue()
        assertEquals(9, royalFlush.value.handType)
        val notRoyalFlush = Hand()
        notRoyalFlush.cards = arrayListOf(
            Card(0, 1), Card(9, 0),
            Card(10, 0),
            Card(11, 0),
            Card(12, 0)
        )
        notRoyalFlush.calcValue()
        assertNotEquals(9, notRoyalFlush.value.handType)

        val notRoyalFlush2 = Hand()
        notRoyalFlush2.cards = arrayListOf(
            Card(1, 0), Card(9, 0),
            Card(10, 0),
            Card(11, 0),
            Card(12, 0)
        )
        notRoyalFlush2.calcValue()
        assertNotEquals(9, notRoyalFlush2.value.handType)
    }
}