package com.gildedrose

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class TextTestFixtureTest {
        @Test
        fun `items update correctly after one day`() {
            val items = listOf(
                Item("Aged Brie", 2, 0),
                Item("Elixir of the Mongoose", 5, 7),
                Item("Sulfuras, Hand of Ragnaros", 0, 80),
                Item("Sulfuras, Hand of Ragnaros", -1, 80),
                Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                Item("Conjured Mana Cake", 3, 6)
            )

            val app = GildedRose(items)
            app.updateQuality()

            assertEquals(1, items[0].sellIn) // Aged Brie SellIn decreases
            assertEquals(1, items[0].quality) // Aged Brie increases in quality

            assertEquals(4, items[1].sellIn) // Normal item SellIn decreases
            assertEquals(6, items[1].quality) // Normal item Quality decreases

            assertEquals(0, items[2].sellIn) // Sulfuras SellIn remains unchanged
            assertEquals(80, items[2].quality) // Sulfuras Quality remains 80

            assertEquals(-1, items[3].sellIn) // Sulfuras SellIn remains unchanged
            assertEquals(80, items[3].quality) // Sulfuras Quality remains 80

            assertEquals(14, items[4].sellIn) // Backstage pass SellIn decreases
            assertEquals(21, items[4].quality) // Backstage pass increases by 1

            assertEquals(9, items[5].sellIn) // Backstage pass SellIn decreases
            assertEquals(50, items[5].quality) // Quality capped at 50

            assertEquals(4, items[6].sellIn) // Backstage pass SellIn decreases
            assertEquals(50, items[6].quality) // Quality capped at 50

            assertEquals(2, items[7].sellIn) // Conjured item SellIn decreases
            assertEquals(4, items[7].quality) // Conjured item loses 2 quality (6 → 4)
        }
}
